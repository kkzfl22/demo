package com.liujun.io.nio.sockettofile.server;

import com.liujun.io.nio.sockettofile.server.service.ProcessChannel;

public class FileServer {

    public static void main(String[] args) {
        int port = 9001;

        MultiplexerFileService server = new MultiplexerFileService(port);

        new Thread(server, "NIO-MultiplexerTimeServer-001").start();

        // 生成处理线程
        new Thread(new ProcessChannel(), "process-001").start();
    }

}
