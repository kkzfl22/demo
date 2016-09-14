package com.liujun.io.nio.trans.bean;

/**
 * 通道附加对象信息
* 源文件名：ChannelMsg.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年8月19日
* 修改作者：liujun
* 修改日期：2016年8月19日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class ChannelAttachMsg {

    /**
     * 类型信息
    * @字段说明 key
    */
    private int key;

    /**
     * 存储的id
    * @字段说明 id
    */
    private int id;

    /**
     * 传输通道信息
    * @字段说明 transChannel
    */
    private FileMemonyChannel transChannel;

    /**
    * 构造方法
    * @param key
    */
    public ChannelAttachMsg(int key) {
        super();
        this.key = key;
    }

    /**
     * @return the key
     */
    public int getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * @return the transChannel
     */
    public FileMemonyChannel getTransChannel() {
        return transChannel;
    }

    /**
     * @param transChannel the transChannel to set
     */
    public void setTransChannel(FileMemonyChannel transChannel) {
        this.transChannel = transChannel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ChannelAttachMsg [key=");
        builder.append(key);
        builder.append(", id=");
        builder.append(id);
        builder.append(", transChannel=");
        builder.append(transChannel);
        builder.append("]");
        return builder.toString();
    }

}
