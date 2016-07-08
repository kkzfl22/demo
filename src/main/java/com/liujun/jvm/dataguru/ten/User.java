package com.liujun.jvm.dataguru.ten;

/**
* 源文件名：User.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年7月8日
* 修改作者：liujun
* 修改日期：2016年7月8日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class User implements Print {

    /**
     * 静态变量
    * @字段说明 OUTPUNAME
    */
    private static final String OUTPUNAME = "out_name";

    /**
     * 用户名
    * @字段说明 name
    */
    private String name;

    /**
     * 年龄
    * @字段说明 age
    */
    private int age;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [name=");
        builder.append(name);
        builder.append(", age=");
        builder.append(age);
        builder.append("]");
        return builder.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.liujun.jvm.dataguru.ten.PrintSys#output()
     */
    @Override
    public String output() {
        return this.toString();
    }

}
