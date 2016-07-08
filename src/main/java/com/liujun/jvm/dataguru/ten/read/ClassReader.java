package com.liujun.jvm.dataguru.ten.read;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.liujun.jvm.dataguru.ten.read.console.JvmClassIntFlag;
import com.liujun.util.IOutils;

/**
 * 进行类文件信息读取
* 源文件名：ClassReader.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年7月8日
* 修改作者：liujun
* 修改日期：2016年7月8日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class ClassReader {

    public static void main(String[] args) {
        String interfacePath = ClassReader.class.getClassLoader()
                .getResource("com/liujun/jvm/dataguru/ten/classout/Print.class").getPath();

        String classPath = ClassReader.class.getClassLoader()
                .getResource("com/liujun/jvm/dataguru/ten/classout/User.class").getPath();

        ClassReader classLoad = new ClassReader();

        classLoad.readInterfaceFile(interfacePath);

        System.out.println(interfacePath);
        System.out.println(classPath);

    }

    public void readInterfaceFile(String file) {
        FileInputStream input = null;

        byte[] javaFlag = new byte[4];
        try {
            input = new FileInputStream(file);
            // 读取开始头，是否为JAVA语言
            input.read(javaFlag);

            Integer temp = byteToInt2(javaFlag);

            // 读取到java语言的标识
            if (temp.equals(JvmClassIntFlag.JAVA_LANGUAGE_FLAG.getFlag())) {
                System.out.println("当前为java语言");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOutils.closeStream(input);
        }
    }

    /**
     * 将byte转换为int值
    * 方法描述
    * @param b
    * @return
    * @创建日期 2016年7月8日
    */
    public static int byteToInt2(byte[] b) {
        int mask = 0xff;
        int temp = 0;
        int n = 0;
        for (int i = 0; i < 4; i++) {
            n <<= 8;
            temp = b[i] & mask;
            n |= temp;
        }
        return n;
    }
}
