package com.liujun.memory;

import java.nio.ByteBuffer;

public class TestMem {

    public static void main(String[] args) {
        MemoryPool pool = new MemoryPool(256, 2048, (short) 5);
        ByteBuffer buffer = pool.allocate(2048);
        ByteBuffer buffer1 = pool.allocate(2048);
        ByteBuffer buffer2 = pool.allocate(2048);
        ByteBuffer buffer3 = pool.allocate(2048);
        ByteBuffer buffer4 = pool.allocate(2048);
        System.out.println("进行空间申请3:" + buffer3);
        System.out.println("进行空间申请:" + buffer4);

        buffer4.position(512);
        buffer4.limit(1025);

        // 进行内存的归还
        // pool.recycle(buffer);
        // pool.recycle(buffer1);
        // pool.recycle(buffer2);
        // pool.recycle(buffer3);
        pool.recycleNotUse(buffer4);

        ByteBuffer buffer6 = pool.allocate(512);
        
        System.out.println("进行空间申请:" + buffer6);

    }

}
