package com.liujun.thread.reactor.demo.singlethread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.tools.ant.taskdefs.Manifest.Section;

/**
 * Reactor 负责响应IO事件，一旦发生，广播发送给相应的Handler去处理,这类似于AWT的thread
 * 
 * @author liujun
 * 
 * @date 2015年4月27日
 * @vsersion 0.0.1
 */
public class Reactor implements Runnable {

	/**
	 * 日志信息
	 */
	private Logger log = Logger.getLogger(Reactor.class);

	/**
	 * 通道选择器对象
	 */
	private Selector select;

	/**
	 * 服务端通道对象
	 */
	private ServerSocketChannel serverChannel;

	public Reactor(int port) throws IOException {
		// 打开通道选择器对象
		select = Selector.open();
		// 打开服务端通道对象
		serverChannel = ServerSocketChannel.open();
		// 得到本地地址信息
		InetSocketAddress loclAddress = new InetSocketAddress(InetAddress.getLocalHost(), port);
		// 绑定本地地址
		serverChannel.socket().bind(loclAddress);
		// 设置当前为非阻塞模式
		serverChannel.configureBlocking(false);
		
		
		// 向select注册channel,当通道为tcp三握手完成之后激活
		SelectionKey selectkey = serverChannel.register(select, SelectionKey.OP_ACCEPT);

		// 利用SelectionKey的attach功能，绑定Acceptor对象，如果有事情，则触发Acceptor
		selectkey.attach(new Acceptor());
		
		log.info("Reactor start over....");
	}

	
	public static void main(String[] args) {
		try {
			new Thread(new Reactor(9001)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void run() {

		try {
			while (!Thread.interrupted()) {
				select.select();
				Set selected = select.selectedKeys();  
				Iterator it = selected.iterator();
				while (it.hasNext()) {
					dispath((SelectionKey)it.next());
					it.remove(); 
				}
			}
		} catch (Exception e) {
			log.error("Reactor IOException", e);
		}
	}

	public void dispath(SelectionKey key) {
		Runnable r = (Runnable) (key.attachment());
		if (r != null) {
			r.run();
		}
	}

	class Acceptor implements Runnable {

		@Override
		public void run() {
			// 调用处理业务的hander
			log.info("Acceptor ready acceptor");
			try {
				// 接受通道连接
				SocketChannel c = serverChannel.accept();

				if (c != null) {
					// 调用相应的业务类来处理
					new SocketReadHandler(select, c);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
}
