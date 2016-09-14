package com.liujun.io.nio.trans.conn;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.liujun.io.nio.trans.bean.FileMemonyChannel;
import com.liujun.io.nio.trans.tran.TransProxyFile;

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

    /**
     * 转换的队列A
     * @字段说明 fileTransB
     */
    private Map<Integer, FileMemonyChannel> fileTransChannel = new ConcurrentHashMap<Integer, FileMemonyChannel>();

    /**
     * 后端连接数量
    * @字段说明 num
    */
    private volatile int endIndex = 0;

    /**
     * 前端连接
    * @字段说明 firstIndex
    */
    private volatile int firstIndex = 0;

    /**
     * 注册后端连接并返回后端的传递队列id
    * 方法描述
    * @return
    * @创建日期 2016年8月20日
    */
    public int regEndConn() {

        FileMemonyChannel fileChannel = new FileMemonyChannel();
        // 设置后端连接
        fileChannel.setTransProxyB(new TransProxyFile());
        int currIndex = endIndex;
        // 放入通道信息
        fileTransChannel.put(currIndex, fileChannel);
        endIndex++;
        // 返回当前的id
        return currIndex;
    }

    /**
     * 注册前端连接
    * 方法描述
    * @return
    * @创建日期 2016年8月20日
    */
    public int regFirstConn() {
        int currIndex = firstIndex;
        // 获取通道信息
        FileMemonyChannel fileChannel = fileTransChannel.get(currIndex);
        // 如果通道存在
        if (null != fileChannel) {
            // 注册后端连接信息
            fileChannel.setTransProxyA(new TransProxyFile());
            firstIndex++;
        }

        return currIndex;
    }

    /**
     * 得到连接通道信息
    * 方法描述
    * @param index 通过索引信息
    * @return
    * @创建日期 2016年8月20日
    */
    public FileMemonyChannel getFileChannel(int index) {
        return fileTransChannel.get(index);
    }
}
