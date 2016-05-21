package com.liujun.thread.reactor.demo.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;

public class TimeClientHandle implements Runnable {

	private Logger log = Logger.getLogger(TimeClientHandle.class);

	/**
	 * 服务器的地址
	 */
	private String host;

	/**
	 * 端口
	 */
	private int port;

	/**
	 * 多路复用选择器对象
	 */
	private Selector selector;

	/**
	 * socket通道
	 */
	private SocketChannel sc;

	/**
	 * 当前的是否停止,默认false，开始状态
	 */
	private AtomicBoolean stop = new AtomicBoolean();

	public TimeClientHandle(String host, int port) {
		this.host = host == null ? "www.liujun.com" : host;
		this.port = port;

		try {
			selector = Selector.open();
			// 打开客户端流通道
			sc = SocketChannel.open();
			// 设置为非阻塞模式
			sc.configureBlocking(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// 进行与服务器的发送对话
		try {
			this.doConnection();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 进行消息的接收
		while (!stop.get()) {
			try {
				// 设置1秒轮循一次
				selector.select(2000);
				// 检查是否有数据接入
				Set<SelectionKey> setKey = selector.selectedKeys();
				// 进行遍历
				Iterator<SelectionKey> iterKey = setKey.iterator();

				while (iterKey.hasNext()) {
					SelectionKey itemKey = iterKey.next();
					iterKey.remove();
					try {
						this.receive(itemKey);
					} catch (Exception e) {
						e.printStackTrace();
						itemKey.cancel();
						if (itemKey.channel() != null) {
							itemKey.channel().close();
						}
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {

			Set<SelectionKey> setKey = selector.selectedKeys();
			// 进行遍历
			Iterator<SelectionKey> iterKey = setKey.iterator();

			while (iterKey.hasNext()) {
				iterKey.next().channel().close();
			}
			selector.close();
		} catch (IOException e) {
			e.printStackTrace();
			log.error("run close Exception",e);
		}

	}

	public void receive(SelectionKey itemKey) throws IOException, InterruptedException {
		// 如果当前键是有效的
		if (itemKey.isValid()) {
			// 得到服务器端的通道
			SocketChannel sc = (SocketChannel) itemKey.channel();
			// 如果当前的键为连接
			if (itemKey.isConnectable()) {
				// 如果已经完成连接
				if (sc.finishConnect()) {
					sc.register(selector, SelectionKey.OP_READ);
					doSend(sc);

				}
			}
			// 如果当前通道为读取操作
			else if (itemKey.isReadable()) {
				ByteBuffer buf = ByteBuffer.allocate(512);
				int readBSize = sc.read(buf);

				if (readBSize > 0) {
					buf.flip();

					byte[] bytebuff = new byte[buf.remaining()];

					buf.get(bytebuff);

					String body = new String(bytebuff, "UTF-8");
					
					log.info("service to client Recive rsp:" + body);
					
					i = Long.parseLong(body);
					
					i++;
					if (i > 100000) {
						this.stop.set(true);
					}
				} else if (readBSize < 0) {
					itemKey.cancel();
					sc.close();
				} else {
					;
				}
				sc.register(selector, SelectionKey.OP_WRITE);
			} else if (itemKey.isWritable()) {
				doSend(sc);
				sc.register(selector, SelectionKey.OP_READ);
			}
		}
	}

	/**
	 * 进行连接
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void doConnection() throws IOException, InterruptedException {
		// 如果服用服务器的连接已经连接成功，需要将读取消息注册到多路复用器上，然后发送消息
		if (sc.connect(new InetSocketAddress(host, port))) {
			// 注册读取操作到多路复用器上
			sc.register(selector, SelectionKey.OP_READ);

			// 进行消息的发送
			this.doSend(sc);
		} else {
			// 注册连接
			sc.register(selector, SelectionKey.OP_CONNECT);
		}
	}

	long i = 0;

	private void doSend(SocketChannel sc) throws IOException, InterruptedException {
		ByteBuffer buff = ByteBuffer.allocate(512);

		//String show ="TimeClientHandle doSend QUERY TIME ORDER " + i + " \n";
		String show = String.valueOf(i);

		byte[] sendbyt = show.toString().getBytes();

		buff.put(sendbyt);

		buff.flip();

		sc.write(buff);

		buff.clear();

		if (!buff.hasRemaining()) {
			log.info(" client Send order 2 server succeed....");
		}
	}

}
