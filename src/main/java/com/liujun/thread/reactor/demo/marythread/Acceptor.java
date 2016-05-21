package com.liujun.thread.reactor.demo.marythread;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.apache.log4j.Logger;

public class Acceptor implements OperatorInf {
	
	/**
	 * 用于处理连接请求
	 */
	private Logger log = Logger.getLogger(Acceptor.class);
	
	/**
	 * 通道选择器对象
	 */
	private final Selector select;

	/**
	 * 服务端通道对象
	 */
	private final ServerSocketChannel serverChannel;
	

	public Acceptor(Selector select, ServerSocketChannel serverChannel) {
		this.select = select;
		this.serverChannel = serverChannel;
	}



	@Override
	public void oper() throws Exception {

		// 调用处理业务的hander
		log.info("Acceptor ready acceptor activate");
		try {
			// 接受通道连接
			SocketChannel c = serverChannel.accept();

			if (c != null) {
				// 调用相应的业务类来处理
				new SocketHandler(select, c);
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Acceptor IOException error", e);
			throw e;
		}

		log.info("Acceptor ready acceptor disable");

	}


	public Selector getSelect() {
		return select;
	}



	public ServerSocketChannel getServerChannel() {
		return serverChannel;
	}
	
	
	

}
