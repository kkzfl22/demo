package com.liujun.io.nio.trans.tran;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

import com.liujun.io.nio.sockettofile.tran.FromTo;
import com.liujun.util.IOutils;

/**
 * 前端读取数据到后端
* 源文件名：FirstReadToEndChannel.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年8月11日
* 修改作者：liujun
* 修改日期：2016年8月11日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class FirstReadToEndChannel implements Runnable {

    /**
     * 前端传送的通道信息
    * @字段说明 channel
    */
    private LinkedChannel<SocketChannel> firstChannel;

    /**
     * 后端接收数据的通道
    * @字段说明 endChannel
    */
    private LinkedChannel<FileChannel> endChannel;

    @Override
    public void run() {

        String url = FromTo.class.getClassLoader().getResource("com/liujun/io/nio/sockettofile/server/service")
                .getPath();

        url = url + "/proces.data";

        SocketChannel sock = null;

        while ((sock = firstChannel.take()) != null) {
            FileOutputStream output = null;
            FileChannel outchannel = null;
            try {
                output = new FileOutputStream(url);
                outchannel = output.getChannel();

                ByteBuffer buff = ByteBuffer.allocate(512);

                // 进行原始通道中的数据读取
                while (sock.read(buff) != -1) {
                    buff.flip();
                    outchannel.write(buff);
                    buff.clear();
                }

                // 完成通道数据写入后将数据传递给
                endChannel.pull(outchannel);

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOutils.closeStream(outchannel);
                IOutils.closeStream(output);
            }
        }
    }

}
