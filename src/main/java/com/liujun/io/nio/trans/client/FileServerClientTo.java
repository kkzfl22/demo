package com.liujun.io.nio.trans.client;

public class FileServerClientTo {

    public static void main(String[] args) {
        int port = 9002;

        new Thread(new FileClientHandleTo(null, port), "TimeClient-002").start();

    }
}
