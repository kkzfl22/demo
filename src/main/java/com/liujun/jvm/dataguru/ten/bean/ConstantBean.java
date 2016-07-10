package com.liujun.jvm.dataguru.ten.bean;

/**
 * 常量池中的实体信息
* 源文件名：ConstantBean.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年7月10日
* 修改作者：liujun
* 修改日期：2016年7月10日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class ConstantBean {

    /**
     * 用来描述长度，或者key的信息
    * @字段说明 length
    */
    private byte[] key;

    /**
     * 值或者描述 信息
    * @字段说明 value
    */
    private byte[] value;

    public ConstantBean(byte[] key) {
        super();
        this.key = key;
    }

    public ConstantBean(byte[] key, byte[] value) {
        this.value = value;
    }

    public byte[] getKey() {
        return key;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

}
