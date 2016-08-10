package com.liujun.io.nio.trans.client;

public class TimeServerClient {
	
	public static void main(String[] args) {
		int port = 9001;
		
		new Thread(new TimeClientHandle(null, port), "TimeClient-001")
		        .start();

	}

}
