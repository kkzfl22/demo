package com.liujun.io.nio.trans.tran;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;

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
public class TransProxyByte implements TransProxyInf {

    private static final TransProxyByte tranProxy = new TransProxyByte();

    /**
     * 消息信息
    * @字段说明 msg
    */
    private ArrayList<byte[]> msg = new ArrayList<>();

    private TransProxyByte() {
    }

    public static TransProxyByte getInstance() {
        return tranProxy;
    }

    /**
     * 将网络通道转换为file
    * 方法描述
    * @param socketChanel
    * @创建日期 2016年8月11日
    */
    public void tranFrom(SocketChannel socketChanel) throws IOException {

        ByteBuffer buff = ByteBuffer.allocate(512);

        while (socketChanel.read(buff) != 0) {
            buff.flip();
            msg.add(buff.array());
            buff.clear();
        }

    }

    /**
     * 将数据写入后端通道信息
    * 方法描述
    * @param toChannel
    * @创建日期 2016年8月11日
    */
    public void tranTo(SocketChannel toChannel) throws IOException {
        if (!msg.isEmpty()) {
            Iterator<byte[]> iter = msg.iterator();

            byte[] item = null;

            ByteBuffer buff = ByteBuffer.allocate(512);

            while (iter.hasNext()) {
                item = iter.next();
                buff.put(item);
                buff.flip();
                toChannel.write(buff);
                buff.clear();
                iter.remove();
            }

        }
    }

}
