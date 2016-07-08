package com.liujun.jvm.dataguru.ten.read.console;

/**
* 源文件名：JvmClass.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年7月8日
* 修改作者：liujun
* 修改日期：2016年7月8日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public enum JvmClassIntFlag {

    JAVA_LANGUAGE_FLAG(0xCAFEBABE, "java语言标识");

    /**
     * 
    * @字段说明 flag
    */
    private int flag;

    private String value;

    /**
    * 构造方法
    * @param flag
    * @param value
    */
    private JvmClassIntFlag(int flag, String value) {
        this.flag = flag;
        this.value = value;
    }

    /**
     * @return the flag
     */
    public int getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

}
