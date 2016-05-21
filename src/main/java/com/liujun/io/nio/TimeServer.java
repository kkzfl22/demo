package com.liujun.io.nio;

public class TimeServer {

	
	public static void main(String[] args) {
		int port = 9001;
		
		
		MultiplexerTimeService server = new MultiplexerTimeService(port);
		
		new Thread(server,"NIO-MultiplexerTimeServer-001").start();
	}
	
}
