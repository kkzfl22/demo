package com.liujun.jvm.dataguru.nine;

/**
* 源文件名：TestMain.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年6月29日
* 修改作者：liujun
* 修改日期：2016年6月29日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class TestMain {

    public static void main(String[] args) {
        int maxValue = 1000000;
        int threadNum = 1000;

        TestLockAddition lock = new TestLockAddition();
        TestNotLockAddition unlock = new TestNotLockAddition();
        TestAtomicAddition atomic = new TestAtomicAddition();

        lock.testLock(maxValue, threadNum);
        unlock.testLock(maxValue, threadNum);
        atomic.testLock(maxValue, threadNum);

    }
}
