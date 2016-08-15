package com.liujun.io.nio.trans.tran;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import com.liujun.io.nio.sockettofile.tran.FromTo;
import com.liujun.util.IOutils;

/**
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

    private static final TransProxyFile tranProxy = new TransProxyFile();

    /**
     * 转换的信息
    * @字段说明 isTran
    */
    private AtomicBoolean isTran = new AtomicBoolean(false);

    /**
     * 生成文件的路径信息
    * @字段说明 url
    */
    private String url = FromTo.class.getClassLoader().getResource("com/liujun/io/nio/trans/tran").getPath()
            + "/proces.data";

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
     * 通道信息
    * @字段说明 channel
    */
    private FileChannel channel = null;

    private TransProxyFile() {
        this.openFile();
    }

    public static TransProxyFile getInstance() {
        return tranProxy;
    }

    /**
     * 将网络通道转换为file
    * 方法描述
    * @param socketChanel
    * @创建日期 2016年8月11日
    */
    public void tranFrom(SocketChannel socketChanel) throws IOException {
        if (!channel.isOpen()) {
            this.openFile();
        }

        long tranFrom = 0;

        // 读取源通道
        if ((tranFrom = channel.transferFrom(socketChanel, fileSize.get(), 512)) > 0) {
            fileSize.set(fileSize.get() + tranFrom);
        }
    }

    /**
     * 将数据写入后端通道信息
    * 方法描述
    * @param toChannel
    * @创建日期 2016年8月11日
    */
    public void tranTo(SocketChannel toChannel) throws IOException {

        long writeSize = 0;
        // 进行目标通道的写入
        while ((writeSize = channel.transferTo(writePostion.get(), fileSize.get(), toChannel)) > 0) {
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
            } finally {
                lock.release();
            }
        }

    }

    /**
     * 进行文件流通道的打开操作
    * 方法描述
    * @创建日期 2016年8月15日
    */
    public void openFile() {
        try {
            randomFile = new RandomAccessFile(url, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        channel = randomFile.getChannel();
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
