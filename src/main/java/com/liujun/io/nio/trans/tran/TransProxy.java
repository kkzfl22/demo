package com.liujun.io.nio.trans.tran;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicBoolean;

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
public class TransProxy {

    private static final TransProxy tranProxy = new TransProxy();

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

    private TransProxy() {

    }

    public static TransProxy getInstance() {
        return tranProxy;
    }

    /**
     * 将网络通道转换为file
    * 方法描述
    * @param socketChanel
    * @创建日期 2016年8月11日
    */
    public void tranFile(SocketChannel socketChanel) {

        if (!isTran.get()) {

            RandomAccessFile randomFile = null;
            FileChannel channel = null;

            try {
                randomFile = new RandomAccessFile(url, "rw");
                channel = randomFile.getChannel();

                // 读取长通道中的数据大小
                ByteBuffer buffer = ByteBuffer.allocate(4);
                socketChanel.read(buffer);
                buffer.flip();
                int size = buffer.getInt();

                // 转换为文件
                channel.transferFrom(socketChanel, 0, size);

                // 设置转换标识为true
                isTran.set(true);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOutils.closeStream(channel);
                IOutils.closeStream(randomFile);
            }
        }
    }

    /**
     * 将数据写入后端通道信息
    * 方法描述
    * @param toChannel
    * @创建日期 2016年8月11日
    */
    public void writeChannel(SocketChannel toChannel) {

        if (isTran.get()) {
            RandomAccessFile randomFile = null;
            FileChannel channel = null;

            try {
                randomFile = new RandomAccessFile(url, "rw");
                channel = randomFile.getChannel();
                // 转换为文件
                channel.transferTo(0, channel.size(), toChannel);
                // 设置转换标识为true
                isTran.set(false);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOutils.closeStream(channel);
                IOutils.closeStream(randomFile);
            }
        }
    }

}
