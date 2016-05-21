package com.liujun.thread.reactor.demo.singlethread;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 进行通道数据读取操作
 * 
 * @author liujun
 * 
 * @date 2015年4月28日
 * @vsersion 0.0.1
 */
public class SocketReadHandler implements Runnable {

	/**
	 * 通道对象
	 */
	private final SocketChannel socketChann;

	/**
	 * 通道选择key
	 */
	private final SelectionKey selectKey;

	/**
	 * 读取对象信息
	 */
	private static final int READING = 0;

	/**
	 * 构造通告初始化信息
	 * 
	 * @param sc
	 *            通道选择器对象
	 * @param c
	 *            通道对象
	 * @throws IOException
	 */
	public SocketReadHandler(Selector sctorParam, SocketChannel scParam) throws IOException {
		this.socketChann = scParam;
		socketChann.configureBlocking(false);
		// 将当前读取数据的信息注册到通道中
		this.selectKey = socketChann.register(sctorParam, READING);
		// 将SelectionKey绑定为本Handler 下一步有事件触发时，将调用本类的run方法。
		selectKey.attach(this);
		// 将当前通道的数据标识为可读
		selectKey.interestOps(SelectionKey.OP_READ);
		// 使尚未返回的第一个选择操作立即返回。
		sctorParam.wakeup();
	}
	
	long num = 0;

	@Override
	public void run() {

		if (selectKey.isReadable()) {
			ByteBuffer buffByt = ByteBuffer.allocate(512);

			StringBuilder showMsg = new StringBuilder();

			// 读取数据
			try {
				while ((socketChann.read(buffByt)) != 0) {
					buffByt.flip();
					showMsg.append(new String(buffByt.array()));
					buffByt.clear();
				}
			} catch (IOException e) {
				e.printStackTrace();
				// 异常处理有问题
			}

			System.out.println("server socketReadHandler msg :" + showMsg.toString());

			// 改变当前通道状态
			try {
				socketChann.register(selectKey.selector(), SelectionKey.OP_WRITE);
				selectKey.attach(this);
				selectKey.interestOps(SelectionKey.OP_WRITE);
			} catch (ClosedChannelException e) {
				e.printStackTrace();
			}

		} else if (selectKey.isWritable()) {
			ByteBuffer buffByt = ByteBuffer.allocate(512);
			// 读取数据
			try {
				buffByt.put(("service to response :" + num).toString().getBytes());
				System.out.println("socketReadHandler rspone msg :111111111111111111");
				buffByt.flip();
				socketChann.write(buffByt);
				buffByt.clear();
				num++;
			} catch (IOException e) {
				e.printStackTrace();
				// 异常处理有问题
			} 

			try {
				socketChann.register(selectKey.selector(), SelectionKey.OP_READ);
				selectKey.attach(this);
				selectKey.interestOps(SelectionKey.OP_READ);
			} catch (ClosedChannelException e) {
				e.printStackTrace();
			}

		}

	}

}
