package com.liujun.io.nio.sockettofile;

public class FileServerClientTo {

    public static void main(String[] args) {
        int port = 9001;

        new Thread(new FileClientHandleTo(null, port), "TimeClient-002").start();

    }

}
