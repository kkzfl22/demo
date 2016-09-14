package com.liujun.io.nio.trans.bean;

import com.liujun.io.nio.trans.tran.TransProxyInf;

/**
 * 进行构造内存传输通道信息
 * 
* 源文件名：FileMemonyChannel.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年8月19日
* 修改作者：liujun
* 修改日期：2016年8月19日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class FileMemonyChannel {

    /**
     * 内存转换块A转换接口信息
    * @字段说明 transProxy
    */
    private TransProxyInf transProxyA;

    /**
     * 内存转换块A转换接口信息
     * @字段说明 transProxy
     */
    private TransProxyInf transProxyB;

    public FileMemonyChannel() {

    }

    /**
    * 构造方法
    * @param transProxyA
    * @param transProxyB
    */
    public FileMemonyChannel(TransProxyInf transProxyA, TransProxyInf transProxyB) {
        this.transProxyA = transProxyA;
        this.transProxyB = transProxyB;
    }

    /**
     * @return the transProxyA
     */
    public TransProxyInf getTransProxyA() {
        return transProxyA;
    }

    /**
     * @param transProxyA the transProxyA to set
     */
    public void setTransProxyA(TransProxyInf transProxyA) {
        this.transProxyA = transProxyA;
    }

    /**
     * @return the transProxyB
     */
    public TransProxyInf getTransProxyB() {
        return transProxyB;
    }

    /**
     * @param transProxyB the transProxyB to set
     */
    public void setTransProxyB(TransProxyInf transProxyB) {
        this.transProxyB = transProxyB;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FileMemonyChannel [transProxyA=");
        builder.append(transProxyA);
        builder.append(", transProxyB=");
        builder.append(transProxyB);
        builder.append("]");
        return builder.toString();
    }

}
