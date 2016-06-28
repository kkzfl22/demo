package com.liujun.jvm.dataguru.eight;

/**
 * 坐标信息
* 源文件名：Print.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年6月23日
* 修改作者：liujun
* 修改日期：2016年6月23日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class Print {

    /**
     * x的坐标
    * @字段说明 x
    */
    private int x;

    /**
     * y的坐标
    * @字段说明 y
    */
    private int y;

    /**
    * 构造方法
    * @param x
    * @param y
    */
    public Print(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Print [x=");
        builder.append(x);
        builder.append(", y=");
        builder.append(y);
        builder.append("]");
        return builder.toString();
    }

}
