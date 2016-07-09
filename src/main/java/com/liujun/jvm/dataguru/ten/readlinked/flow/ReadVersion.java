package com.liujun.jvm.dataguru.ten.readlinked.flow;

import java.io.FileInputStream;

import com.liujun.jvm.dataguru.ten.readlinked.SeqLinkedList;
import com.liujun.jvm.dataguru.ten.readlinked.ServiceExecInf;
import com.liujun.jvm.dataguru.ten.readlinked.console.JvmClassStrFlag;
import com.liujun.jvm.dataguru.ten.util.PrintHex;

public class ReadVersion implements ServiceExecInf {

    @Override
    public boolean invoke(SeqLinkedList seqList) throws Exception {

        FileInputStream input = (FileInputStream) seqList.getValue(JvmClassStrFlag.COMM_INPUT_STREAM.getFlag());

        // 小版本号
        byte[] minorVersion = new byte[2];

        input.read(minorVersion);

        // 小版本号
        Integer minorVersionNum = PrintHex.byteToInt2(minorVersion);

        // 主版本版本号
        byte[] majorVersion = new byte[2];

        input.read(majorVersion);

        // 主版本号
        Integer majorVersionNum = PrintHex.byteToInt2(majorVersion);

        System.out.println("联合版本号为:" + majorVersionNum + "." + minorVersionNum);

        return seqList.nextExec();
    }

    @Override
    public boolean rollBackInvoke(SeqLinkedList seqList) throws Exception {
        return seqList.rollExec();
    }

}
