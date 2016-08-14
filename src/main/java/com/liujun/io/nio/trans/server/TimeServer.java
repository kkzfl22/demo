package com.liujun.io.nio.trans.server;

import java.io.IOException;
import java.nio.channels.Selector;

import com.liujun.io.nio.trans.console.Config;

public class TimeServer {

    public static void main(String[] args) throws IOException {

        // 多通道使用一个多路选择器
        Selector selector = Selector.open();

        // 初始化端口信息
        // 前端端口
        int firstPort = 9001;
        // 后端端口
        int endPost = 9002;

        MultiplexerPortMsgService port1Init = new MultiplexerPortMsgService();
        // 进行端口1前端的初始化操作
        port1Init.portInit(firstPort, selector, Config.CONFIG_TYPE_FIRST.getKey());
        // 进行端口2后端的初始化操作
        port1Init.portInit(endPost, selector, Config.CONFIG_TYPE_END.getKey());

        // 进行多路选择器的遍历
        MultiplexerAllService allService = new MultiplexerAllService(selector);
        new Thread(allService, "TimeServer port-001").start();
    }

}
