package com.liujun.jvm.dataguru.six;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
public class HotLoad {

    private String basePath = "D:\\java\\work\\self\\demo\\target\\classes\\";

    private CustomClassLoader customLoad = null;

    @SuppressWarnings("rawtypes")
    public Class loadClass(String name) throws ClassNotFoundException {
        customLoad = new CustomClassLoader();
        return customLoad.findClass(name);
    }

    /**
     * 为检查文件是否有修改,做文件大小的存储
     */
    Map<String, Integer> checkMap = new HashMap<String, Integer>();

    /**
     * 检查文件是否有修改
     * @param name 文件路径
     * @return
     */
    public boolean checkUpd(String name) {

        if (checkMap.get(name) != null) {
            int upd = checkMap.get(name);

            int newSize = this.getFileSize(name);

            if (upd == newSize) {
                return false;
            }
            checkMap.put(name, newSize);

            return true;
        } else {
            int newSize = this.getFileSize(name);
            checkMap.put(name, newSize);

            return true;
        }
    }

    /**
     * 得到文件大小
     * @param name
     * @return
     */
    private int getFileSize(String name) {
        FileInputStream input = null;
        try {
            String path = className2Path(name);
            input = new FileInputStream(path);
            return input.available();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(input);
        }

        return 0;
    }

    private String className2Path(String name) {
        return basePath + name.replace(".", "/") + ".class";
    }

    @Autowired
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        HotLoad loader = new HotLoad();

        Class<?> clazz = null;
        try {
            // 进行重新加载操作
            while (true) {
                String name = "com.liujun.jvm.dataguru.six.Worker";
                // 检查文件是否被修改
                if (loader.checkUpd(name)) {
                    System.out.println("发现修改,重新加载...");
                    clazz = loader.loadClass(name);
                }
                clazz.getMethod("doit").invoke(clazz);
                // 2秒之后重新加载
                Thread.currentThread().sleep(2000);
            }

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
