package com.liujun.jvm.dataguru.six;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 进行class字节的热加载
* 源文件名：HotLoad.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年6月8日
* 修改作者：liujun
* 修改日期：2016年6月8日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class HotLoad extends ClassLoader {

    private String basePath = "E:\\java\\self\\demo\\target\\classes\\";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("进行类加载...");

        name = "com.liujun.jvm.dataguru.six.Worker";

        // 得到指定路径文件的byte信息
        String path = className2Path(name);

        byte[] bytBuff = this.getByte(path);

        Class clazz = null;

        clazz = defineClass(name, bytBuff, 0, bytBuff.length);

        return clazz;
    }

    private String className2Path(String name) {
        return basePath + name.replace(".", "/") + ".class";
    }

    public byte[] getByte(String path) {
        byte[] rsp = null;

        FileInputStream input = null;
        try {
            input = new FileInputStream(path);
            rsp = new byte[input.available()];
            input.read(rsp);

            return rsp;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        HotLoad loader = new HotLoad();

        System.out.println("重新加载");
        Class<?> clazz;
        try {
            // 进行重新加载操作
            clazz = loader.loadClass("com.liujun.jvm.dataguru.six.Worker");
            // Object obj = clazz.newInstance();
            clazz.getMethod("doit").invoke(clazz);
            // 10秒之后重新加载
            Thread.currentThread().sleep(5000);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
