package com.liujun.pattern.bridge.pattern;

public abstract class BridgeSender {

	/**
	 * 发送的接口操作
	 */
	private Sendable send;

	public Sendable getSend() {
		return send;
	}

	public void setSend(Sendable send) {
		this.send = send;
	}

	/**
	 * 进行发送操作
	 * 
	 * @throws Exception
	 */
	public abstract void send() throws Exception;
}
