package com.liujun.memory;

import java.nio.ByteBuffer;

/**
 * java 内存池的实现
* 源文件名：MemoryPool.java
* 文件版本：1.0.0
* 创建作者：liujun
* 创建日期：2016年12月19日
* 修改作者：liujun
* 修改日期：2016年12月19日
* 文件描述：TODO
* 版权所有：Copyright 2016 zjhz, Inc. All Rights Reserved.
*/
public class MemoryPool {

    /**
     * 内存池对象信息
    * @字段说明 pool
    */
    private final PageMemory[] POOL;

    /**
     * 每个chunk的大小
    * @字段说明 CHUNK_SIZE
    */
    private final int CHUNK_SIZE;

    /**
     * 当前已经分配的大小
    * @字段说明 allocationIndex
    */
    private int allocationIndex = 0;

    /**
     * 用来构建内存池对象信息
    * 构造方法
    * @param chunkSize
    * @param memorySize
    * @param poolSize
    */
    public MemoryPool(int chunkSize, int memorySize, short poolSize) {
        CHUNK_SIZE = chunkSize;
        // 进行每个内存页的初始化
        POOL = new PageMemory[poolSize];
        // 进行每个chunk的页面的分配内存操作
        for (int i = 0; i < poolSize; i++) {
            POOL[i] = new PageMemory(ByteBuffer.allocateDirect(memorySize),
                    CHUNK_SIZE);
        }
    }

    /**
     * 进行内存分配操作
    * 方法描述
    * @param size 需要的内存大小,最好CHUNK_SIZE的倍数，以方便 内存的回收利用
    * @return
    * @创建日期 2016年12月19日
    */
    public ByteBuffer allocate(int size) {
        //计算需要的chunk大小
        int needChunk = size % CHUNK_SIZE == 0 ? size / CHUNK_SIZE
                : size / CHUNK_SIZE + 1;
        //

        return null;
    }

}
