package com.liujun.io.nio.sockettofile;

public class FileServerClientFrom {

    public static void main(String[] args) {
        int port = 9001;

        new Thread(new FileClientHandleFrom(null, port), "TimeClient-001").start();

    }

}
