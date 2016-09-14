package com.liujun.io.nio.trans.tran;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicLong;

import com.liujun.util.IOutils;

/**
 * 前端的数据向后端传输通道
* 源文件名：TransProxy.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年8月11日
* 修改作者：liujun
* 修改日期：2016年8月11日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class TransProxyFile implements TransProxyInf {

    /**
     * 生成文件的路径信息
    * @字段说明 url
    */
    private String url;

    /**
     * 随机文件读取流程
    * @字段说明 randomFile
    */
    private RandomAccessFile randomFile = null;

    /**
     * 文件内容信息
    * @字段说明 fileSize
    */
    private AtomicLong fileSize = new AtomicLong(0);

    /**
     * 文件写入的position
    * @字段说明 writePost
    */
    private AtomicLong writePostion = new AtomicLong(0);

    /**
     * 转换的数字
    * @字段说明 numLong
    */
    private AtomicLong numLong = new AtomicLong(0);

    /**
     * 通道信息
    * @字段说明 channel
    */
    private FileChannel channel = null;

    /**
     * 文件时间
    * @字段说明 fileTime
    */
    private long fileTime;

    public TransProxyFile() {
        this.openFile();
    }

    /**
     * 将网络通道转换为file
    * 方法描述
    * @param socketChanel
    * @创建日期 2016年8月11日
    */
    public boolean tranFrom(SocketChannel socketChanel) throws IOException {
        if (!channel.isOpen()) {
            this.openFile();
        }

        long tranFrom = 0;

        // 读取源通道
        if ((tranFrom = channel.transferFrom(socketChanel, fileSize.get(), 512)) > 0) {
            fileSize.set(fileSize.get() + tranFrom);
            numLong.incrementAndGet();
        }

        if (tranFrom > 0) {
            // 标识数据已经转换到通道中
            // System.out.println("读取A完成,大小:" + fileSize.get());
            return true;
        }

        return false;
    }

    /**
     * 将数据写入后端通道信息
    * 方法描述
    * @param toChannel
    * @创建日期 2016年8月11日
    */
    public boolean tranTo(SocketChannel toChannel) throws IOException {

        long writeSize = 0;

        // if (numLong.get() == 1) {
        // System.out.println("进行文件清空");
        // // 写入完成后清空文件通道信息
        // FileLock lock = channel.tryLock();
        //
        // try {
        // // 进行文件清空
        // channel.truncate(0);
        // // 文件大小也被清空
        // fileSize.set(0);
        // // 设置写入的游标为0
        // writePostion.set(0);
        // } finally {
        // lock.release();
        // }
        // numLong.incrementAndGet();
        //
        // return false;
        // }

        // 进行目标通道的写入
        while ((writeSize = channel.transferTo(writePostion.get(), fileSize.get(), toChannel)) > 0) {
            // System.out.println(
            // "写入B完成,postion:" + writePostion.get() + ",通道大小:" + channel.size()
            // + ",fileSize:" + fileSize.get());
            // 设置文件的大小信息
            writePostion.set(writePostion.get() + writeSize);
        }

        // 如果有数据写入，才进行判断置空操作
        if (writePostion.get() > 0) {
            // 写入完成后清空文件通道信息
            FileLock lock = channel.tryLock();

            try {
                // 进行文件清空
                channel.truncate(0);
                // 文件大小也被清空
                fileSize.set(0);
                // 设置写入的游标为0
                writePostion.set(0);
            } finally {
                lock.release();
            }

            return true;
        }

        return false;

    }

    /**
     * 进行文件流通道的打开操作
    * 方法描述
    * @创建日期 2016年8月15日
    */
    public void openFile() {
        try {
            // 系统时间纳秒数，以保证文件不重复
            fileTime = System.nanoTime();
            url = url + "/trans";
            System.out.println("系统路径:" + url + "/" + fileTime);
            randomFile = new RandomAccessFile(url + "/" + fileTime, "rw");
            channel = randomFile.getChannel();
            channel.truncate(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重置连接信息
    * 方法描述
    * @创建日期 2016年8月19日
    */
    public void reset() {
        // 写入完成后清空文件通道信息
        FileLock lock = null;
        try {
            lock = channel.tryLock();
            // 进行文件清空
            channel.truncate(0);
            // 文件大小也被清空
            fileSize.set(0);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                lock.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭流信息
    * 方法描述
    * @创建日期 2016年8月13日
    */
    public void close() {

        // 写入完成后清空文件通道信息
        FileLock lock = null;
        try {
            lock = channel.tryLock();

            // 进行文件清空
            channel.truncate(0);
            // 文件大小也被清空
            fileSize.set(0);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                lock.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        IOutils.closeStream(channel);
        IOutils.closeStream(randomFile);
    }

}
