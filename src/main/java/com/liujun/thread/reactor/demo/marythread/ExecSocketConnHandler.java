package com.liujun.thread.reactor.demo.marythread;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

/**
 * 执行数据的发送和接受任务
 * 
 * @author liujun
 * 
 * @date 2015年4月30日
 * @vsersion 0.0.1
 */
public class ExecSocketConnHandler implements Runnable {

	/**
	 * 日志信息
	 */
	private Logger log = Logger.getLogger(ExecSocketConnHandler.class);

	/**
	 * 从队列中获取数据并进行操作
	 */
	private final LinkedBlockingQueue<Acceptor> queue;

	public ExecSocketConnHandler(LinkedBlockingQueue<Acceptor> queue) {
		this.queue = queue;
	}

	public void run() {
		Acceptor handler = null;
		while (true) {
			try {
				handler = queue.take();
				// 进行相关的业务执行操作
				handler.oper();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("ExcecSocketHandler run error: oper method", e);
			}
		}

	}

}
