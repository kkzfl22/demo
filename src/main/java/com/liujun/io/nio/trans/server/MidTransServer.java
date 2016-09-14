package com.liujun.io.nio.trans.server;

import java.io.IOException;
import java.nio.channels.Selector;

import com.liujun.io.nio.trans.conn.TransConnectManager;

/**
 * 中间转换服务
* 源文件名：MidTransServer.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年8月18日
* 修改作者：liujun
* 修改日期：2016年8月18日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class MidTransServer {

    private int size = 50;

    /**
     * 进行中间转换服务的
    * 方法描述
    * @throws IOException
    * @创建日期 2016年8月18日
    */
    public void run() throws IOException {

        // 多通道使用一个多路选择器
        Selector selector = Selector.open();
        // 初始化端口信息
        // 前端端口
        int firstPort = 9001;

        String host = "www.liujun.com";

        MultiplexerPortMsgService port1Init = new MultiplexerPortMsgService();
        // 进行端口1前端的初始化操作
        port1Init.portInit(host, firstPort, selector);
        // 进行端口2后端的初始化操作
        // port1Init.portInit(endPost, selector,
        // Config.CONFIG_TYPE_END.getKey());

        // 启动连接另外的端口信息
        FileServerHandleTo handler = new FileServerHandleTo();

        // 生成通道的信息,针对前端的信息
        // 生成多个后端的连接通道
        for (int i = 0; i < size; i++) {
            handler.openNewSocketChann("192.168.3.10", 3306, selector);
        }

        // handler.setToConnInfo("www.liujun.com", 3001, selector, attachEnd);

        TransConnectManager tan = new TransConnectManager();

        // 进行多路选择器的遍历
        MultiplexerAllService allService = new MultiplexerAllService(selector, tan);
        new Thread(allService, "TimeServer port-001").start();

        // 进行多路选择器的遍历

    }

    public static void main(String[] args) throws IOException {

        MidTransServer trans = new MidTransServer();
        trans.run();

    }
}
