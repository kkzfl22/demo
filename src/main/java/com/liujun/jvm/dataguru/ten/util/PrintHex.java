package com.liujun.jvm.dataguru.ten.util;

public class PrintHex {

    /**
     * 将4byte转换为int值
    * 方法描述
    * @param b
    * @return
    * @创建日期 2016年7月8日
    */
    public static int byteToInt4(byte[] b) {
        int mask = 0xff;
        int temp = 0;
        int n = 0;
        for (int i = 0; i < 4; i++) {
            n <<= 8;
            temp = b[i] & mask;
            n |= temp;
        }
        return n;
    }

    /**
     * 将2byte转换为int值
     * 方法描述
     * @param b
     * @return
     * @创建日期 2016年7月8日
     */
    public static int byteToInt2(byte[] b) {
        int mask = 0xff;
        int temp = 0;
        int n = 0;
        for (int i = 0; i < 2; i++) {
            n <<= 8;
            temp = b[i] & mask;
            n |= temp;
        }
        return n;
    }
}