package com.liujun.io.nio.trans.client;

public class TimeServerClientFrom {
	
	public static void main(String[] args) {
		int port = 9001;
		
		new Thread(new TimeClientHandleFrom(null, port), "TimeClient-001")
		        .start();

	}

}
