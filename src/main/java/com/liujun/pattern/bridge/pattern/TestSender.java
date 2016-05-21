package com.liujun.pattern.bridge.pattern;

public class TestSender {

	public static void main(String[] args) throws Exception {
		BridgeSender bridSend = new DriverBridge();

		// 调用短信发送
		Sendable sms = new SmsSender();
		bridSend.setSend(sms);
		bridSend.send();

		// 调用邮件发送
		Sendable email = new EmailSender();
		bridSend.setSend(email);
		bridSend.send();
	}

}
