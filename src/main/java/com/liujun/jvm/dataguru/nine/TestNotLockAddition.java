package com.liujun.jvm.dataguru.nine;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 测试有锁在多线程下的情况
* 源文件名：TestLockAddition.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年6月29日
* 修改作者：liujun
* 修改日期：2016年6月29日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class TestNotLockAddition {

    public static void testLock(final int maxValue, final int maxThread) {

        final NotLockAddition lock = new NotLockAddition();

        long startTime = System.currentTimeMillis();

        // 等待的线程数
        int waitThread = maxThread + 1;

        final CyclicBarrier cyc = new CyclicBarrier(waitThread);

        for (int i = 0; i < maxThread; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (;;) {
                        int value = lock.add();
                        if (value >= maxValue) {
                            break;
                        }
                    }

                    try {
                        cyc.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        try {
            cyc.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("无锁执行时间:" + (endTime - startTime));
    }

}
