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
public class FileMemonyChannelBean {

    /**
     * 内存转换块A转换接口信息
    * @字段说明 transProxy
    */
    private TransProxyInf transProxy;

    /**
     * 当前是否正在使用
    * @字段说明 isUse
    */
    private boolean currUse;

    /**
    * 构造方法
    * @param transProxyA
    * @param transProxyB
    */
    public FileMemonyChannelBean() {
        super();
    }

    /**
     * @return the transProxy
     */
    public TransProxyInf getTransProxy() {
        return transProxy;
    }

    /**
     * @param transProxy the transProxy to set
     */
    public void setTransProxy(TransProxyInf transProxy) {
        this.transProxy = transProxy;
    }

    /**
     * @return the currUse
     */
    public boolean isCurrUse() {
        return currUse;
    }

    /**
     * @param currUse the currUse to set
     */
    public void setCurrUse(boolean currUse) {
        this.currUse = currUse;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FileMemonyChannel [transProxy=");
        builder.append(transProxy);
        builder.append(", currUse=");
        builder.append(currUse);
        builder.append("]");
        return builder.toString();
    }

}
