package com.liujun.io.nio.trans.otherService;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

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
public class MultiplexerPortMsgService {

    /**
     * 端口初始化信息
    * 方法描述
    * @param port
    * @param selector
    * @创建日期 2016年8月14日
    */
    public void portInit(String hostin, int port, Selector selector, int type) {

        String host = hostin == null ? "www.liujun.com" : hostin;
        try {

            // 1,打开ServerSocketChannel，用于监听客户端的连接，它是所有客户端连接的父管道
            ServerSocketChannel serverchannel = ServerSocketChannel.open();
            // 2,绑定监听端口，设置连接为非阻塞模式
            serverchannel.socket().bind(new InetSocketAddress(host, port));
            serverchannel.configureBlocking(false);
            // 3,创建多路复用器
            // selector = Selector.open();
            // 4,注册ACCEPT事件到通道中，监听ACCEPT事情
            serverchannel.register(selector, SelectionKey.OP_ACCEPT, type);
            System.out.println("the time server start in port :" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

}
