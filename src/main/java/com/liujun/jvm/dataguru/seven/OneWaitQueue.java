package com.liujun.jvm.dataguru.seven;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 单一等待队列实现
* 源文件名：OneWaitQueue.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年6月19日
* 修改作者：liujun
* 修改日期：2016年6月19日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class OneWaitQueue {

    /**
     * 设置最大的队列大小
    * @字段说明 MAX_SIZE
    */
    private static final int MAX_SIZE = 1;

    /**
     * 进行等待的队列实现
    * @字段说明 isRun
    */
    private List<Object> queueRun = new ArrayList<Object>(MAX_SIZE);

    /**
     * 当前使用独占锁
    * @字段说明 lock
    */
    private Lock lock = new ReentrantLock();

    /**
     * 获得放入通道的控制锁
    * @字段说明 putCondition
    */
    private Condition putCondition = lock.newCondition();

    /**
     * 获得获取通道的控制锁
    * @字段说明 getConditon
    */
    private Condition getConditon = lock.newCondition();

    /**
     * 放入方法
    * 方法描述
    * @param input
    * @throws InterruptedException
    * @创建日期 2016年6月19日
    */
    public void put(Object input) throws InterruptedException {

        lock.lock();
        try {
            if (queueRun.size() > MAX_SIZE) {
                putCondition.await();
            }
            queueRun.add(input);
            // 让读取通道获得锁的控制权
            getConditon.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        Object value = null;
        lock.lock();
        try {
            if (queueRun.size() == 0) {
                getConditon.await();
            }
            value = queueRun.remove(0);
            // 让读取通道获得锁的控制权
            putCondition.signal();
        } finally {
            lock.unlock();
        }

        return value;
    }

}
