package com.liujun.pattern.decorate;

import java.util.HashMap;
import java.util.Map;

/**
* 源文件名：Decorate.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年8月18日
* 修改作者：liujun
* 修改日期：2016年8月18日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class Decorate {

    private Map<Integer, ServiceInf> runMap = new HashMap<Integer, ServiceInf>();

    public void run() throws Exception {
        // 添加加密
        runMap.put(1, null);
        // 添加其他操作

        // 输入的转换
        int[] input = new int[3];

        String msg = "";

        for (int i : input) {
            msg = runMap.get(i).runExec(msg);
        }

    }

}
