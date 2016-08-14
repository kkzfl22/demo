package com.liujun.io.nio.trans.tran;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
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
     * 通道信息
    * @字段说明 channel
    */
    private FileChannel channel = null;

    private TransProxyFile() {
        try {
            randomFile = new RandomAccessFile(url, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        channel = randomFile.getChannel();
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

        // 读取长通道中的数据大小
        ByteBuffer buffer = ByteBuffer.allocate(4);
        socketChanel.read(buffer);
        buffer.flip();
        int size = buffer.getInt();

        // 转换为文件
        channel.transferFrom(socketChanel, 0, size);
    }

    /**
     * 将数据写入后端通道信息
    * 方法描述
    * @param toChannel
    * @创建日期 2016年8月11日
    */
    public void tranTo(SocketChannel toChannel) throws IOException {

        // 转换为文件
        channel.transferTo(0, channel.size(), toChannel);

        // 进行文件清空
        channel.truncate(0);

    }

    /**
     * 关闭流信息
    * 方法描述
    * @创建日期 2016年8月13日
    */
    public void close() {
        IOutils.closeStream(channel);
        IOutils.closeStream(randomFile);
    }

}
