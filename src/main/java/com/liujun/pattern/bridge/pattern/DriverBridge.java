package com.liujun.pattern.bridge.pattern;

public class DriverBridge extends BridgeSender {

	@Override
	public void send() throws Exception {
		getSend().send();
	}

}
