package com.liujun.jvm.dataguru.ten.readlinked;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.liujun.jvm.dataguru.ten.bean.ClassBeanInfo;
import com.liujun.jvm.dataguru.ten.readlinked.console.JvmClassStrFlag;
import com.liujun.jvm.dataguru.ten.readlinked.flow.AccessFlags;
import com.liujun.jvm.dataguru.ten.readlinked.flow.ReadClass;
import com.liujun.jvm.dataguru.ten.readlinked.flow.ReadConstant;
import com.liujun.jvm.dataguru.ten.readlinked.flow.ReadField;
import com.liujun.jvm.dataguru.ten.readlinked.flow.ReadJavaLanguage;
import com.liujun.jvm.dataguru.ten.readlinked.flow.ReadMethod;
import com.liujun.jvm.dataguru.ten.readlinked.flow.ReadVersion;
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

        // System.out.println("接口读取....");
        // classLoad.readInterfaceFile(interfacePath);
        // System.out.println("接口读取结束....");

        System.out.println("----------");
        System.out.println("类信息读取....");
        classLoad.readInterfaceFile(classPath);
        System.out.println("类信息读取结束....");

        System.out.println(interfacePath);
        System.out.println(classPath);

    }

    public void readInterfaceFile(String file) {
        FileInputStream input = null;

        // 进行class字节码读取流程
        SeqLinkedList classRead = new SeqLinkedList();

        try {
            input = new FileInputStream(file);

            classRead.putParam(JvmClassStrFlag.COMM_INPUT_STREAM.getFlag(), input);

            // 读取jvm标识
            classRead.addExec(new ReadJavaLanguage());
            // 读取JVM版本号
            classRead.addExec(new ReadVersion());
            // 读取常量池大小
            classRead.addExec(new ReadConstant());
            // 读取修饰符
            classRead.addExec(new AccessFlags());
            // 加载类的信息
            classRead.addExec(new ReadClass());
            // 进行属性的读取
            classRead.addExec(new ReadField());
            // 进行方法的读取
            classRead.addExec(new ReadMethod());

            classRead.nextExec();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            IOutils.closeStream(input);
        }

        ClassBeanInfo rspBean = (ClassBeanInfo) classRead.getValue(JvmClassStrFlag.COMM_RSP_MSG.getFlag());

        System.out.println("rsp msg:" + rspBean);

    }

}
