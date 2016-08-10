package com.liujun.io.nio.sockettofile.server.bean;

import java.nio.channels.FileChannel;

public class ProcessMsg {

    /**
     * 用于标识数据是读取还是写入
    * @字段说明 flag
    */
    private int flag;

    /**
     * 写入的大小
    * @字段说明 size
    */
    private int size;

    /**
     * 通道信息
    * @字段说明 channinfo
    */
    private FileChannel channinfo;

    public ProcessMsg(int flag, int size, FileChannel channinfo) {
        super();
        this.flag = flag;
        this.size = size;
        this.channinfo = channinfo;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public FileChannel getChanninfo() {
        return channinfo;
    }

    public void setChanninfo(FileChannel channinfo) {
        this.channinfo = channinfo;
    }

}
