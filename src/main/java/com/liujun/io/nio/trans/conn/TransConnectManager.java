package com.liujun.io.nio.trans.conn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.liujun.io.nio.trans.bean.FileMemonyChannel;
import com.liujun.io.nio.trans.bean.FileMemonyChannelBean;
import com.liujun.io.nio.trans.tran.TransProxyInf;

/**
 * 进行连接通道的管理
* 源文件名：TransConnectManager.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年8月19日
* 修改作者：liujun
* 修改日期：2016年8月19日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class TransConnectManager {

    private static final int SIZE = 5;

    /**
     * 前端连接
    * @字段说明 proxyA
    */
    private FileMemonyChannelBean[] tranA = new FileMemonyChannelBean[SIZE];

    /**
     * 进行创建连接的索引
    * @字段说明 transIndex
    */
    private int transIndex = 0;

    /**
     * 前端连接
    * @字段说明 proxyA
    */
    private FileMemonyChannelBean[] tranB = new FileMemonyChannelBean[SIZE];

    /**
     * 完成的通道信息
    * @字段说明 chann
    */
    private List<FileMemonyChannel> chann = new ArrayList<FileMemonyChannel>();

    /**
     * 进行创建连接的索引
    * @字段说明 transIndex
    */
    private int transBIndex = 0;

    /**
     * 独占锁信息
    * @字段说明 lock
    */
    private Lock lock = new ReentrantLock();

    /**
     * 注册后台连接信息
     * 方法描述
     * @param trans
     * @创建日期 2016年8月19日
     */
    public void regFirstConn(TransProxyInf trans) {
        tranA[transIndex].setCurrUse(false);
        tranA[transIndex].setTransProxy(trans);
    }

    /**
     * 注册后台连接信息
    * 方法描述
    * @param trans
    * @创建日期 2016年8月19日
    */
    public void regEndConn(TransProxyInf trans) {
        tranB[transBIndex].setCurrUse(false);
        tranB[transBIndex].setTransProxy(trans);

        lock.lock();
        FileMemonyChannel rsp = new FileMemonyChannel();
        try {
            // 获取内存通道A的信息
            for (FileMemonyChannelBean fileMemonyChannelBean : tranA) {
                if (!fileMemonyChannelBean.isCurrUse()) {
                    fileMemonyChannelBean.setCurrUse(true);
                    rsp.setTransProxyA(fileMemonyChannelBean.getTransProxy());
                    break;
                }
            }
            // 获取内存通道B的信息
            for (FileMemonyChannelBean fileMemonyChannelBean : tranB) {
                if (!fileMemonyChannelBean.isCurrUse()) {
                    fileMemonyChannelBean.setCurrUse(true);
                    rsp.setTransProxyB(fileMemonyChannelBean.getTransProxy());
                    break;
                }
            }

            chann.add(rsp);
        } finally {
            lock.unlock();
        }
    }

}
