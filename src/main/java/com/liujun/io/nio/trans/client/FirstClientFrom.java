package com.liujun.io.nio.trans.client;

/**
 * 前端模拟数据发送
* 源文件名：FirstClientFrom.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年8月18日
* 修改作者：liujun
* 修改日期：2016年8月18日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class FirstClientFrom {

    public void run() {
        int port = 9001;

        new Thread(new FirstHandleFrom("www.liujun.com", port), "TimeClient-001").start();
    }

}
