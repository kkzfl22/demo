package com.liujun.io.nio.trans.server;

public class TimeServer {

    public static void main(String[] args) {
        // 前端的数据
        int port = 9001;
        MultiplexerFirstService server = new MultiplexerFirstService(port);
        new Thread(server, "NIO-MultiplexerFirstService-001").start();

        // 后端的连接
        int portEnd = 9002;
        MultiplexerEndService serverEnd = new MultiplexerEndService(portEnd);
        new Thread(serverEnd, "NIO-MultiplexerEndService-001").start();
    }

}
