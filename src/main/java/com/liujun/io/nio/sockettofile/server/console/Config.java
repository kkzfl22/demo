package com.liujun.io.nio.sockettofile.server.console;

/**
 * 配制信息
* 源文件名：Config.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年8月10日
* 修改作者：liujun
* 修改日期：2016年8月10日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public enum Config {

    /**
     * 当前类型代表来源
    * @字段说明 FLAG_TYPE_FROM
    */
    FLAG_TYPE_FROM(1),

    /**
     * 当前代表目标数据 
    * @字段说明 FLAG_TYPE_TO
    */
    FLAG_TYPE_TO(2),;

    /**
     * 配制信息
    * @字段说明 flag
    */
    private int flag;

    private Config(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

}
