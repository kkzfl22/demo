package com.liujun.io.nio.trans.server;

public class TimeServer {

	
	public static void main(String[] args) {
		int port = 9001;
		
		
		MultiplexerFirstService server = new MultiplexerFirstService(port);
		
		new Thread(server,"NIO-MultiplexerTimeServer-001").start();
	}
	
}
