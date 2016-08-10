package com.liujun.jvm.dataguru.ten.readlinked.flow;

import java.io.FileInputStream;

import com.liujun.jvm.dataguru.ten.readlinked.SeqLinkedList;
import com.liujun.jvm.dataguru.ten.readlinked.ServiceExecInf;
import com.liujun.jvm.dataguru.ten.readlinked.console.JvmClassIntFlag;
import com.liujun.jvm.dataguru.ten.readlinked.console.JvmClassStrFlag;
import com.liujun.jvm.dataguru.ten.util.PrintHex;

/**
 * 进行读取java语言标识
* 源文件名：ReadJavaLanguage.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年7月9日
* 修改作者：liujun
* 修改日期：2016年7月9日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class ReadJavaLanguage implements ServiceExecInf {

    @Override
    public boolean invoke(SeqLinkedList seqList) throws Exception {

        FileInputStream input = (FileInputStream) seqList.getValue(JvmClassStrFlag.COMM_INPUT_STREAM.getFlag());

        byte[] javaFlag = new byte[4];

        input.read(javaFlag);

        Integer temp = PrintHex.byteToInt4(javaFlag);

        // 读取到java语言的标识
        if (temp.equals(JvmClassIntFlag.JAVA_LANGUAGE_FLAG.getFlag())) {
            System.out.println("读取到java语言标识");
        }

        return seqList.nextExec();
    }

    @Override
    public boolean rollBackInvoke(SeqLinkedList seqList) throws Exception {
        return seqList.rollExec();
    }

}
