package com.liujun.io.nio.trans.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import com.liujun.io.nio.trans.bean.ChannelAttachMsg;

public class FileServerHandleTo {

    /**
     * 服务器的地址
     */
    private String host;

    /**
     * 端口
     */
    private int port;

    /**
     * 多路复用选择器对象
     */
    private Selector selector;

    /**
     * socket通道
     */
    private SocketChannel sc;

    public void setToConnInfo(String host, int port, Selector selectorIn, ChannelAttachMsg msg) {
        this.host = host == null ? "www.liujun.com" : host;
        this.port = port;

        try {
            this.selector = selectorIn;
            // 打开客户端流通道
            sc = SocketChannel.open();
            // 设置为非阻塞模式
            sc.configureBlocking(false);

            // 注册连接信息
            this.doConnection(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 进行连接
     * 
     * @throws IOException
     */
    private void doConnection(ChannelAttachMsg msg) throws IOException {
        // 如果服用服务器的连接已经连接成功，需要将读取消息注册到多路复用器上，然后发送消息
        if (sc.connect(new InetSocketAddress(host, port))) {
            // 注册读取操作到多路复用器上
            sc.register(selector, SelectionKey.OP_READ, msg);
        } else {
            // 注册连接
            sc.register(selector, SelectionKey.OP_CONNECT, msg);
        }
    }

}
