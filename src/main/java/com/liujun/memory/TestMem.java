package com.liujun.memory;

import java.nio.ByteBuffer;

public class TestMem {

    public static void main(String[] args) {
        MemoryPool pool = new MemoryPool(256, 1024, (short) 5);
        ByteBuffer buffer = pool.allocate(1024);
        ByteBuffer buffer1 = pool.allocate(1024);
        ByteBuffer buffer2 = pool.allocate(1024);
        ByteBuffer buffer3 = pool.allocate(1024);
        ByteBuffer buffer4 = pool.allocate(1025);
        System.out.println("进行空间申请3:" + buffer3);
        System.out.println("进行空间申请:" + buffer4);

        // 进行内存的归还
        pool.recycle(buffer);
        pool.recycle(buffer1);
        pool.recycle(buffer2);
        pool.recycle(buffer3);
        // pool.recycle(buffer4);

    }

}
