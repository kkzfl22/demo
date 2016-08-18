package com.liujun.pattern.decorate;

/**
* 源文件名：ServiceInf.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年8月18日
* 修改作者：liujun
* 修改日期：2016年8月18日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public interface ServiceInf {

    /**
     * 进行执行操作
    * 方法描述
    * @param msg
    * @return
    * @throws Exception
    * @创建日期 2016年8月18日
    */
    public String runExec(String msg) throws Exception;

}
