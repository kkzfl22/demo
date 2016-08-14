package com.liujun.io.nio.trans.tran;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * 转换接口
* 源文件名：TransProxyInf.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年8月14日
* 修改作者：liujun
* 修改日期：2016年8月14日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public interface TransProxyInf {

    /**
     * 针对来源进行转换
    * 方法描述
    * @param socketChanel
    * @throws IOException
    * @创建日期 2016年8月14日
    */
    public void tranFrom(SocketChannel socketChanel) throws IOException;

    /**
     * 针对目录文件转换
    * 方法描述
    * @param socketChanel
    * @throws IOException
    * @创建日期 2016年8月14日
    */
    public void tranTo(SocketChannel socketChanel) throws IOException;

}
