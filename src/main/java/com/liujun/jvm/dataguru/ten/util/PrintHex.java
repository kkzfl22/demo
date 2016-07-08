package com.liujun.jvm.dataguru.ten.util;

public class PrintHex {
    // 备选字符
    static final char digits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static void main(String[] args) {
        parseInt(-54);
    }

    public static void parseInt(int inputValue) {
        int Num = inputValue;// 要转换的数字
        int length = 32;

        char[] result = new char[length];

        do {

            result[--length] = digits[Num & 15];

            Num >>>= 4;

        } while (Num != 0);

        String value = "0x";

        for (int i = length; i < result.length; i++) {
            value += result[i];
        }

        System.out.println(value);
    }
}