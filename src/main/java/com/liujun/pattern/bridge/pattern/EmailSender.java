package com.liujun.pattern.bridge.pattern;

/**
 * 进行发送email操作
 * 
 * @author liujun
 * 
 * @date 2014年12月16日
 * @vsersion 0.0.1
 */
public class EmailSender implements Sendable {

	@Override
	public void send() throws Exception {
		System.out.println("这是发送email");
	}

}
