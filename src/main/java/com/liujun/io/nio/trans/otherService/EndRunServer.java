package com.liujun.io.nio.trans.otherService;

import java.io.IOException;
import java.nio.channels.Selector;

import com.liujun.io.nio.trans.console.Config;

public class EndRunServer {

    public static void main(String[] args) throws IOException {

        // 多通道使用一个多路选择器
        Selector selector = Selector.open();

        // 初始化端口信息
        // 前端端口
        int firstPort = 3001;

        String host = "192.168.3.2";

        MultiplexerPortMsgService port1Init = new MultiplexerPortMsgService();
        // 进行端口1前端的初始化操作
        port1Init.portInit(host, firstPort, selector, Config.CONFIG_TYPE_FIRST.getKey());

        // 进行多路选择器的遍历
        MultiplexerAllService allService = new MultiplexerAllService(selector);
        new Thread(allService, "TimeServer port-003").start();
    }

}
