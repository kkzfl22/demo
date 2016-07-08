package com.liujun.jvm.dataguru.nine;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 进行有锁的加法操作
* 源文件名：LockAddition.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年6月29日
* 修改作者：liujun
* 修改日期：2016年6月29日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class LockAddition {

    /**
     * 
    * @字段说明 ADD_INIT_VALUE
    */
    private int addInitValue;

    /**
    * 构造方法
    * @param mAX_SIZE
    */
    public LockAddition() {
    }

    /**
     * 使用独点锁
    * @字段说明 lock
    */
    private Lock lock = new ReentrantLock();

    /**
     * 使用有锁的方式进行id的加操作
    * 方法描述
    * @param i
    * @return
    * @创建日期 2016年6月29日
    */
    public int add() {
        lock.lock();
        try {
            return addInitValue++;
        } finally {
            lock.unlock();
        }
    }

    // /**
    // * 使用有锁的方式进行id的加操作
    // * 方法描述
    // * @param i
    // * @return
    // * @创建日期 2016年6月29日
    // */
    // public synchronized int add() {
    // return addInitValue++;
    // }

}
