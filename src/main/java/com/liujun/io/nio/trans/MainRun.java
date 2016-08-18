package com.liujun.io.nio.trans;

import java.io.IOException;

import com.liujun.io.nio.trans.client.FirstClientFrom;
import com.liujun.io.nio.trans.otherService.EndRunServer;
import com.liujun.io.nio.trans.server.MidTransServer;

/**
* 源文件名：MainRun.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年8月18日
* 修改作者：liujun
* 修改日期：2016年8月18日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class MainRun {

    public static void main(String[] args) throws IOException {
        // 1,启动后台模拟服务器
        EndRunServer run = new EndRunServer();
        run.run();

        // 2,启动中间转换程序
        MidTransServer midService = new MidTransServer();
        midService.run();

        // 启动前端模拟发送请求
        FirstClientFrom clientFrom = new FirstClientFrom();
        clientFrom.run();
    }

}
