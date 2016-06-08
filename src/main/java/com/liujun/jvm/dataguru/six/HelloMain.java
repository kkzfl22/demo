package com.liujun.jvm.dataguru.six;

/**
* 源文件名：MainPrint.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年6月8日
* 修改作者：liujun
* 修改日期：2016年6月8日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class HelloMain {

    public static void main(String[] args) {
        while (true) {
            Worker.doit();
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
