package com.liujun.jvm.dataguru.ten.readlinked.flow;

import java.io.FileInputStream;

import com.liujun.jvm.dataguru.ten.readlinked.SeqLinkedList;
import com.liujun.jvm.dataguru.ten.readlinked.ServiceExecInf;
import com.liujun.jvm.dataguru.ten.readlinked.console.JvmClassIntFlag;
import com.liujun.jvm.dataguru.ten.readlinked.console.JvmClassStrFlag;
import com.liujun.jvm.dataguru.ten.util.PrintHex;

/**
 * 读取类的标示符
* 源文件名：AccessFlags.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年7月10日
* 修改作者：liujun
* 修改日期：2016年7月10日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class AccessFlags implements ServiceExecInf {

    @Override
    public boolean invoke(SeqLinkedList seqList) throws Exception {

        FileInputStream input = (FileInputStream) seqList.getValue(JvmClassStrFlag.COMM_INPUT_STREAM.getFlag());

        byte[] javaFlag = new byte[2];

        input.read(javaFlag);

        int flag = PrintHex.byteToInt1(javaFlag[1]);

        System.out.println(Integer.toHexString(flag));

        // 检查是否为公共可访问的

        if (flag == JvmClassIntFlag.ACCESSFLAG_ACC_PUBLIC.getFlag()) {
            System.out.println("当间为公共可访问的,_ACC_PUBLIC");
        }

        if (flag == JvmClassIntFlag.ACCESSFLAG_ACC_FINAL.getFlag()) {
            System.out.println("当前为final 不能被继承的");
        }

        System.out.println("当前类的标示符");

        return seqList.nextExec();
    }

    @Override
    public boolean rollBackInvoke(SeqLinkedList seqList) throws Exception {
        return seqList.rollExec();
    }

}
