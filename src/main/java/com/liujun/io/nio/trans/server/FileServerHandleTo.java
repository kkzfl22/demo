package com.liujun.io.nio.trans.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class FileServerHandleTo {

    public void openNewSocketChann(String host, int port, Selector selectorIn) {
        try {
            // 打开客户端流通道
            SocketChannel sc = SocketChannel.open();
            // 设置为非阻塞模式
            sc.configureBlocking(false);

            // 如果服用服务器的连接已经连接成功，需要将读取消息注册到多路复用器上，然后发送消息
            if (sc.connect(new InetSocketAddress(host, port))) {
                // 注册读取操作到多路复用器上
                sc.register(selectorIn, SelectionKey.OP_READ);
            } else {
                // 注册连接
                sc.register(selectorIn, SelectionKey.OP_CONNECT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
