package com.liujun.jvm.dataguru.eight;

/**
* 源文件名：TestPrint.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年6月23日
* 修改作者：liujun
* 修改日期：2016年6月23日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class TestPrint {

    public static void main(String[] args) throws InterruptedException {
        Print p1 = new Print(1, 21);
        Print p2 = new Print(11, 22);
        Print p3 = new Print(12, 23);
        Print p4 = new Print(13, 24);
        Print p5 = new Print(14, 25);
        Print p6 = new Print(15, 26);
        Print p7 = new Print(16, 27);
        Print p8 = new Print(17, 28);

        Thread.currentThread().sleep(10000);

        p2 = null;
        p3 = null;
        p4 = null;
        p5 = null;
        p6 = null;
        p7 = null;

        Thread.currentThread().sleep(100000000);
    }

}
