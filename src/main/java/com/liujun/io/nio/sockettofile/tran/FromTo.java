package com.liujun.io.nio.sockettofile.tran;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

import com.liujun.util.IOutils;

/**
 * 使用transto调用 
* 源文件名：FromTo.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年8月10日
* 修改作者：liujun
* 修改日期：2016年8月10日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class FromTo {

    public static void main(String[] args) {

        String url = FromTo.class.getClassLoader().getResource("com/liujun/io/nio/sockettofile/tran").getPath();

        String from = url + "/from.data";
        String to = url + "/to.data";

        // tranFromto(from, to);
        trantoFrom(from, to);
    }

    /**
     * 进行从文件读取到写入
    * 方法描述
    * @param from
    * @param to
    * @创建日期 2016年8月10日
    */
    public static void tranFromto(String from, String to) {
        RandomAccessFile fromRandom = null;
        FileChannel fromChannel = null;

        RandomAccessFile toRandom = null;
        FileChannel tochannel = null;

        try {
            fromRandom = new RandomAccessFile(from, "rw");
            fromChannel = fromRandom.getChannel();

            toRandom = new RandomAccessFile(to, "rw");
            tochannel = toRandom.getChannel();
            long num = 0;
            // 进行多次的转换
            while ((num = tochannel.transferFrom(fromChannel, 0, fromChannel.size())) > 0) {
                System.out.println(num);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            IOutils.closeStream(tochannel);
            IOutils.closeStream(toRandom);
            IOutils.closeStream(fromChannel);
            IOutils.closeStream(fromRandom);
        }
    }

    /**
     * 进行从文件读取到写入
     * 方法描述
     * @param from
     * @param to
     * @创建日期 2016年8月10日
     */
    public static void trantoFrom(String from, String to) {
        RandomAccessFile fromRandom = null;
        FileChannel fromChannel = null;

        RandomAccessFile toRandom = null;
        FileChannel tochannel = null;

        try {
            fromRandom = new RandomAccessFile(from, "rw");
            fromChannel = fromRandom.getChannel();

            toRandom = new RandomAccessFile(to, "rw");
            tochannel = toRandom.getChannel();
            // 进行多次的转换
            fromChannel.transferTo(0l, fromChannel.size(), tochannel);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            IOutils.closeStream(tochannel);
            IOutils.closeStream(toRandom);
            IOutils.closeStream(fromChannel);
            IOutils.closeStream(fromRandom);
        }
    }

}
