package com.liujun.jvm.dataguru.nine;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用原子变量进行测试++的情况
* 源文件名：NotLockAddition.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年6月29日
* 修改作者：liujun
* 修改日期：2016年6月29日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class AtomicAddition {

    /**
     *
    * @字段说明 value
    */
    private AtomicInteger value = new AtomicInteger(0);

    /**
     * 使用有锁的方式进行id的加操作
    * 方法描述
    * @param i
    * @return
    * @创建日期 2016年6月29日
    */
    public int add() {
        return value.incrementAndGet();
    }

}
