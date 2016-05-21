package com.liujun.thread.reactor.demo.client;

public class TimeServerClient {

	public static void main(String[] args) {
		int port = 9001;

		for (int i = 0; i < 10; i++) {
			new Thread(new TimeClientHandle(null, port), "TimeClient-001").start();
		}

	}

}
