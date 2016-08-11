package com.liujun.io.nio.trans.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import com.liujun.io.nio.trans.tran.TransProxy;

/**
 * 后端数据读取操作
* 源文件名：MultiplexerEndService.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年8月11日
* 修改作者：liujun
* 修改日期：2016年8月11日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class MultiplexerEndService implements Runnable {

    /**
     * 多路选择器
     */
    private Selector selector;

    /**
     * 多路通道
     */
    ServerSocketChannel serverchannel;

    /**
     * 用来控制整个服务器的开始操作,true,表示开启，false 关闭
     */
    private AtomicBoolean stop = new AtomicBoolean();

    public MultiplexerEndService(int port) {
        try {

            // 1,打开ServerSocketChannel，用于监听客户端的连接，它是所有客户端连接的父管道
            serverchannel = ServerSocketChannel.open();
            // 2,绑定监听端口，设置连接为非阻塞模式
            serverchannel.socket().bind(new InetSocketAddress("www.liujun.com", port));
            serverchannel.configureBlocking(false);
            // 3,创建多路复用器
            selector = Selector.open();
            // 4,注册ACCEPT事件到通道中，监听ACCEPT事情
            serverchannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("the time server start in port :" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void stop() {
        this.stop.set(true);
    }

    @Override
    public void run() {
        // 如果当前开始不是关闭的才能运行
        while (!stop.get()) {
            try {
                // 在此通道阻塞1000毫秒
                selector.select(1000);
                // 轮循进行检查是否有已经准备就绪的key
                Set<SelectionKey> selectkey = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectkey.iterator();

                while (iter.hasNext()) {
                    SelectionKey sekey = iter.next();
                    iter.remove();
                    // 将当前的数据交给对应的方法来处理
                    try {
                        this.handleInput(sekey);
                    } catch (Exception e) {
                        if (null != sekey) {
                            sekey.cancel();
                            if (sekey.channel() != null) {
                                sekey.channel().close();
                            }
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (null != key && key.isValid()) {
            // 如果当前的连接已经用于套接字接受操作
            if (key.isAcceptable()) {
                // 得到当前注册的通道
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();

                // 得到socket通信通道
                SocketChannel sc = ssc.accept();

                // 设置为非阻塞
                sc.configureBlocking(false);

                // 在socket通道中注册一个读取通道
                sc.register(selector, SelectionKey.OP_READ);
            }
            // 检查此键的通道是否已准备好进行读取
            if (key.isReadable()) {
                // 得到当前
                SocketChannel sc = (SocketChannel) key.channel();

                // 检查是否已经生成数据
                TransProxy.getInstance().writeChannel(sc);

            }
        }
    }

}
