package com.liujun.collection;

import java.util.BitSet;

public class BitSetUse {

    private static BitSet bitUtil = new BitSet(1024);

    public static void main(String[] args) {

        for (int i = 0; i < 1024; i++) {
            System.out.print(bitUtil.get(i) + "\t");
            if (i % 64 == 0) {
                System.out.println();
            }
        }

        for (int i = 0; i < 1024; i++) {
            bitUtil.set(i);

        }

        for (int i = 0; i < 1024; i++) {
            System.out.print(bitUtil.get(i) + "\t");
            if (i % 64 == 0) {
                System.out.println();
            }
        }
    }

}
