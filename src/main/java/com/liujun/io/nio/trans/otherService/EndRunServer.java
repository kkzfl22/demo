package com.liujun.io.nio.trans.otherService;

import java.io.IOException;
import java.nio.channels.Selector;

import com.liujun.io.nio.trans.console.Config;

/**
 * 后端模拟服务器
* 源文件名：EndRunServer.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年8月18日
* 修改作者：liujun
* 修改日期：2016年8月18日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class EndRunServer {

    public void run() throws IOException {
        // 多通道使用一个多路选择器
        Selector selector = Selector.open();

        // 初始化端口信息
        // 前端端口
        int firstPort = 3001;

        String host = "www.liujun.com";

        MultiplexerPortMsgService port1Init = new MultiplexerPortMsgService();
        // 进行端口1前端的初始化操作
        port1Init.portInit(host, firstPort, selector, Config.CONFIG_TYPE_FIRST.getKey());

        // 进行多路选择器的遍历
        MultiplexerAllService allService = new MultiplexerAllService(selector);
        new Thread(allService, "TimeServer port-003").start();
    }

}
