package com.liujun.pattern.bridge.pattern;

/**
 * 进行短消息的发送
 * 
 * @author liujun
 * 
 * @date 2014年12月16日
 * @vsersion 0.0.1
 */
public class SmsSender implements Sendable {

	@Override
	public void send() throws Exception {
		System.out.println("这是发送短信");
	}

}
