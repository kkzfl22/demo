package com.liujun.jvm.dataguru.ten.readlinked.console;

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
public enum JvmClassStrFlag {

    /**
     * 进行读取的流信息
    * @字段说明 COMM_INPUT_STREAM
    */
    COMM_INPUT_STREAM("comm_input_stream", ""),

    /**
     * 读取的常量池的信息
     * @字段说明 COMM_INPUT_STREAM
     */
    READ_CONSTANT_INFO("read_constant_info", ""),

    /**
     * 响应输出的信息
    * @字段说明 COMM_RSP_MSG
    */
    COMM_RSP_MSG("comm_rsp_msg", "")

    ;

    /**
     * 
    * @字段说明 flag
    */
    private String flag;

    private String value;

    /**
    * 构造方法
    * @param flag
    * @param value
    */
    private JvmClassStrFlag(String flag, String value) {
        this.flag = flag;
        this.value = value;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
