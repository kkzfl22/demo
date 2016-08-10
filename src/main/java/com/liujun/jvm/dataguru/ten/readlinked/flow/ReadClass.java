package com.liujun.jvm.dataguru.ten.readlinked.flow;

import java.io.FileInputStream;
import java.util.Map;

import com.liujun.jvm.dataguru.ten.bean.ClassBeanInfo;
import com.liujun.jvm.dataguru.ten.bean.ConstantBean;
import com.liujun.jvm.dataguru.ten.readlinked.SeqLinkedList;
import com.liujun.jvm.dataguru.ten.readlinked.ServiceExecInf;
import com.liujun.jvm.dataguru.ten.readlinked.console.JvmClassStrFlag;
import com.liujun.jvm.dataguru.ten.util.PrintHex;

/**
 * 读取文件信息
* 源文件名：ReadClass.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年7月10日
* 修改作者：liujun
* 修改日期：2016年7月10日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class ReadClass implements ServiceExecInf {

    @SuppressWarnings("unchecked")
    @Override
    public boolean invoke(SeqLinkedList seqList) throws Exception {
        FileInputStream input = (FileInputStream) seqList.getValue(JvmClassStrFlag.COMM_INPUT_STREAM.getFlag());
        ClassBeanInfo rspBean = (ClassBeanInfo) seqList.getValue(JvmClassStrFlag.COMM_RSP_MSG.getFlag());

        if (null == rspBean) {
            rspBean = new ClassBeanInfo();
        }

        byte[] javaFlag = new byte[2];

        input.read(javaFlag);

        // 得到索引标识
        int index = PrintHex.byteToInt2(javaFlag);

        // 得到结果信息
        Map<Integer, ConstantBean> contStant = (Map<Integer, ConstantBean>) seqList
                .getValue(JvmClassStrFlag.READ_CONSTANT_INFO.getFlag());

        if (null != contStant) {
            ConstantBean bean = contStant.get(index);

            // 得到类的引用地址索引
            int conIndex = PrintHex.byteToInt2(bean.getKey());
            ConstantBean beanClass = contStant.get(conIndex);

            rspBean.setClassName(new String(beanClass.getValue()));

            System.out.println("当前的类名为:" + rspBean.getClassName());
        }

        // 读取父类信息
        // super_class u2
        // 指向常量池的Class
        byte[] superClass = new byte[2];

        input.read(superClass);

        if (null != contStant) {
            // 得到索引标识
            int indexSuppper = PrintHex.byteToInt2(superClass);

            ConstantBean bean = contStant.get(indexSuppper);

            // 得到类的引用地址索引
            int conIndex = PrintHex.byteToInt2(bean.getKey());

            if (0 != conIndex) {
                ConstantBean beanClass = contStant.get(conIndex);

                rspBean.setSuperClassName(new String(beanClass.getValue()));

                System.out.println("当前的类的父类为:" + rspBean.getSuperClassName());
            }
        }

        // 接口数量
        byte[] interNumByte = new byte[2];
        input.read(interNumByte);

        // 进行转换
        int interNum = PrintHex.byteToInt2(interNumByte);

        for (int i = 0; i < interNum; i++) {
            // 进行读取接口
            byte[] interfaceByte = new byte[2];
            input.read(interfaceByte);

            // 得到索引标识
            int indexInterface = PrintHex.byteToInt2(interfaceByte);

            ConstantBean bean = contStant.get(indexInterface);

            // 得到类的引用地址索引
            int conIndex = PrintHex.byteToInt2(bean.getKey());

            if (0 != conIndex) {
                ConstantBean beanClass = contStant.get(conIndex);

                rspBean.setInterClassName(new String(beanClass.getValue()));

                System.out.println("当前的类的接口为:" + rspBean.getInterClassName());
            }

        }

        seqList.putParam(JvmClassStrFlag.COMM_RSP_MSG.getFlag(), rspBean);

        return seqList.nextExec();
    }

    @Override
    public boolean rollBackInvoke(SeqLinkedList seqList) throws Exception {
        return seqList.rollExec();
    }

}
