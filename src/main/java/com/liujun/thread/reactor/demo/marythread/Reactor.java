package com.liujun.thread.reactor.demo.marythread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

/**
 * Reactor 负责响应IO事件，一旦发生，广播发送给相应的Handler去处理,这类似于AWT的thread
 * 
 * 反应器模式
 * 
 * 此为多线程版本处理，使线程池来处理
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

	/**
	 * 进行任务队列运行信息
	 */
	private LinkedBlockingQueue<SocketHandler> queue = new LinkedBlockingQueue<SocketHandler>(100);
	
	/**
	 * 进行任务队列运行
	 */
	private ExecutorService execPool = Executors.newFixedThreadPool(3);
	
	
	/**
	 * 用于处理连接的队列信息
	 */
	private LinkedBlockingQueue<Acceptor> connQueue = new LinkedBlockingQueue<Acceptor>(100);
	
	
	/**
	 * 进行处理连接的任务队列信息
	 */
	private ExecutorService execConnPool = Executors.newFixedThreadPool(2);

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
		selectkey.attach(new Acceptor(select,serverChannel));
		
		//初始化时放入处理队列
		for (int i = 0; i < 5; i++) {
			//放入消费者队列进行数据回复
			execPool.submit(new ExecSocketRedWriHandler(queue));
		}
		
		//初始化任务连接队列信息
		for (int i = 0; i < 3; i++) {
			execConnPool.submit(new ExecSocketConnHandler(connQueue));
		}
		

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
				select.select(2000);
				Set selected = select.selectedKeys();
				Iterator it = selected.iterator();
				while (it.hasNext()) {
					dispath((SelectionKey) it.next());
					it.remove();
				}
			}
		} catch (Exception e) {
			log.error("Reactor IOException", e);
		}
	}

	public void dispath(SelectionKey key) {
		OperatorInf r = (OperatorInf) (key.attachment());
		if (r != null) {
			// 进行流程操作
			if (r instanceof SocketHandler) {
				SocketHandler handler = (SocketHandler) r;
				// 将当前的流程放入
				try {
					queue.put(handler);
				} catch (InterruptedException e) {
					e.printStackTrace();
					log.error("Reactor dispath InterruptedException:", e);
				}
			} 
			else if (r instanceof SocketHandler) {
				Acceptor handler = (Acceptor) r;
				//放入当前需操作的连接对象信息
				try {
					connQueue.put(handler);
				} catch (InterruptedException e) {
					e.printStackTrace();
					log.error("Reactor dispath InterruptedException:", e);
				}
			} 
			else {
				try {
					r.oper();
				} catch (Exception e) {
					e.printStackTrace();
					log.error("Reactor dispath exception:", e);
					// 将当前发生错误的通道关闭
					SelectableChannel channel = key.channel();
					if (null != channel) {
						try {
							channel.close();
						} catch (IOException e1) {
							e1.printStackTrace();
							log.error("Reactor dispath close exception:", e1);
						}
					}
				}
			}
		}
	}


}
