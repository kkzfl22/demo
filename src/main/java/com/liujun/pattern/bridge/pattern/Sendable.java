package com.liujun.pattern.bridge.pattern;

/**
 * 进行方法执行的接口
 * 
 * @author liujun
 * 
 * @date 2014年12月16日
 * @vsersion 0.0.1
 */
public interface Sendable {

	/**
	 * 发送消息的接口
	 */
	public void send() throws Exception;

}
