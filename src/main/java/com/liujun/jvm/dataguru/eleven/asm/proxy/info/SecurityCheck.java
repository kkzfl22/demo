package com.liujun.jvm.dataguru.eleven.asm.proxy.info;

/**
 * 这是在进行调用之前的安全检查
* 源文件名：SecurityCheck.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年7月17日
* 修改作者：liujun
* 修改日期：2016年7月17日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class SecurityCheck {

    public static boolean checkSecurity() {
        System.out.println("这是在进行安全检查信息");
        return true;
    }

}
