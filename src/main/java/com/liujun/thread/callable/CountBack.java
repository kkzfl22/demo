package com.liujun.thread.callable;

import java.util.concurrent.Callable;

/**
 * 进行异步的计算，完成之后返回结果s
* 源文件名：CountBack.java
* 文件版本：1.0.0
* 创建作者：Think
* 创建日期：2016年12月21日
* 修改作者：Think
* 修改日期：2016年12月21日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class CountBack implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {

        Thread.currentThread().sleep(1000);

        return 2 + 3;
    }

}
