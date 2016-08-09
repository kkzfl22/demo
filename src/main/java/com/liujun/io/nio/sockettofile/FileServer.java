package com.liujun.io.nio.sockettofile;

public class FileServer {

    public static void main(String[] args) {
        int port = 9001;

        MultiplexerFileService server = new MultiplexerFileService(port);

        new Thread(server, "NIO-MultiplexerTimeServer-001").start();
    }

}
