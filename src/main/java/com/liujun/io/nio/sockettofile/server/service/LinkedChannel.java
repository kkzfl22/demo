package com.liujun.io.nio.sockettofile.server.service;

import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 进行先进先出的通道操作
* 源文件名：LinkedQueue.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年8月10日
* 修改作者：liujun
* 修改日期：2016年8月10日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class LinkedChannel {

    /**
     * 例对象
    * @字段说明 INSTANCE
    */
    private static final LinkedChannel INSTANCE = new LinkedChannel();

    /**
     * 待处理的对象
    * @字段说明 linked
    */
    private LinkedList<SocketChannel> channelList = new LinkedList<>();

    /**
     * 独占锁对象
    * @字段说明 lock
    */
    private Lock lock = new ReentrantLock();

    /**
     * 放入锁对象
    * @字段说明 putConk
    */
    private Condition putLonk = lock.newCondition();

    /**
     * 取出锁对象信息
    * @字段说明 takLock
    */
    private Condition takLock = lock.newCondition();

    /**
     * 通道最大数
    * @字段说明 MAXSIZE
    */
    private static final int MAXSIZE = 1024;

    public static LinkedChannel getInstance() {
        return INSTANCE;
    }

    /**
     * 放入信息
    * 方法描述
    * @param channel
    * @创建日期 2016年8月10日
    */
    public void pull(SocketChannel channel) {

        lock.lock();
        try {

            if (channelList.size() > MAXSIZE) {
                putLonk.await();
            }

            channelList.add(channel);
            // 通知获取对象可以进行获取
            takLock.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public SocketChannel getChannel() {

        SocketChannel channel = null;

        lock.lock();
        try {

            if (channelList.size() == 0) {
                takLock.await();
            }
            channel = channelList.removeLast();
            // 通知获取对象可以进行获取
            putLonk.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return channel;
    }
}
