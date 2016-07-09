package com.liujun.jvm.dataguru.ten.readlinked.flow;

import com.liujun.jvm.dataguru.ten.readlinked.SeqLinkedList;
import com.liujun.jvm.dataguru.ten.readlinked.ServiceExecInf;

public class ReadConstant implements ServiceExecInf {

    @Override
    public boolean invoke(SeqLinkedList seqList) throws Exception {
        return false;
    }

    @Override
    public boolean rollBackInvoke(SeqLinkedList seqList) throws Exception {
        return false;
    }

}
