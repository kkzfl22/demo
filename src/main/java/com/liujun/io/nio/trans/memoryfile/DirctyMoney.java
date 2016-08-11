package com.liujun.io.nio.trans.memoryfile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import com.liujun.util.IOutils;

/**
* 源文件名：DirctyMoney.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年8月11日
* 修改作者：liujun
* 修改日期：2016年8月11日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class DirctyMoney {

    /**
     * 缓冲区大小按M单位
    * @字段说明 BUFF_M_SIZE
    */
    private static final int BUFF_M_SIZE = 3;

    /**
     * 直接内存操作
    * 方法描述
    * @param fromPath
    * @param toPath
    * @创建日期 2016年8月11日
    */
    public void directCopyFile(String fromPath, String toPath) {

        FileInputStream input = null;
        FileChannel inputChannel = null;

        FileOutputStream output = null;
        FileChannel outputChannel = null;

        try {
            input = new FileInputStream(fromPath);
            inputChannel = input.getChannel();

            output = new FileOutputStream(toPath);
            MappedByteBuffer buff = inputChannel.map(MapMode.READ_ONLY, 0, inputChannel.size());

            long fileLength = inputChannel.size();

            // 缓冲区大小
            final int BUFFER_SIZE = 1024 * BUFF_M_SIZE;

            // 缓冲区
            byte[] dst = new byte[BUFFER_SIZE];

            // 进行遍历内存映射做写入
            for (int offset = 0; offset < fileLength; offset += BUFFER_SIZE) {
                if (fileLength - offset >= BUFFER_SIZE) {
                    // 此遍历数据与 buff.get(dst, 0, BUFFER_SIZE);等同
                    // for (int i = 0; i < BUFFER_SIZE; i++) {
                    // dst[i] = buff.get(offset + i);
                    // }
                    buff.get(dst, 0, BUFFER_SIZE);

                    output.write(dst, 0, BUFFER_SIZE);
                } else {
                    // for (int i = 0; i < fileLength - offset; i++) {
                    // dst[i] = buff.get(offset + i);
                    // }
                    int offsiteSize = (int) (fileLength - offset);
                    buff.get(dst, 0, offsiteSize);

                    output.write(dst, 0, offsiteSize);
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOutils.closeStream(outputChannel);
            IOutils.closeStream(output);
            IOutils.closeStream(inputChannel);
            IOutils.closeStream(input);
        }
    }

    /**
     * 直接内存操作
     * 方法描述
     * @param fromPath
     * @param toPath
     * @创建日期 2016年8月11日
     */
    public void copyFile(String fromPath, String toPath) {

        FileInputStream input = null;
        FileChannel inputChannel = null;

        FileOutputStream output = null;
        FileChannel outputChannel = null;

        try {
            input = new FileInputStream(fromPath);
            inputChannel = input.getChannel();

            output = new FileOutputStream(toPath);
            outputChannel = output.getChannel();

            ByteBuffer buff = ByteBuffer.allocate(1024 * BUFF_M_SIZE);

            while (inputChannel.read(buff) != -1) {
                buff.flip();
                outputChannel.write(buff);
                buff.clear();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOutils.closeStream(outputChannel);
            IOutils.closeStream(output);
            IOutils.closeStream(inputChannel);
            IOutils.closeStream(input);
        }
    }

    public static void main(String[] args) {
        DirctyMoney money = new DirctyMoney();
        long start = System.currentTimeMillis();
        money.directCopyFile("E:/output/01.mp4", "e:/output/02.mp4");
        long end = System.currentTimeMillis();
        System.out.println("直接内存映射时间:" + (end - start));

        long startuse = System.currentTimeMillis();
        money.copyFile("E:/output/01.mp4", "e:/output/05.mp4");
        long endUse = System.currentTimeMillis();
        System.out.println("普通拷贝时间:" + (endUse - startuse));
    }

}
