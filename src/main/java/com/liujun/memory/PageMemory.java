package com.liujun.memory;

import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.concurrent.atomic.AtomicBoolean;

public class PageMemory {

    /**
     * 内存区域
    * @字段说明 buffer
    */
    private ByteBuffer buffer;

    /**
     * 每个chunk的大小
    * @字段说明 chunkSize
    */
    private int chunkSize;

    /**
     * 总的chunk数 
    * @字段说明 chunkIndex
    */
    private int chunkCount;

    /**
     * 用于标识内存是否使用集合
    * @字段说明 memUseSet
    */
    private final BitSet memUseSet;

    /**
     * 是否锁定标识
    * @字段说明 isLock
    */
    private AtomicBoolean isLock = new AtomicBoolean(false);

    /**
     * 记录内存地址信息
    * @字段说明 address
    */
    private final long address;

    public PageMemory(ByteBuffer buffer, int chunkSize) {
        this.buffer = buffer;

        // 设置chunk的大小
        this.chunkSize = chunkSize;
        // 设置chunk的数量
        this.chunkCount = this.buffer.capacity() / this.chunkSize;
        // 设置当前内存标识块的大小
        this.memUseSet = new BitSet(this.chunkCount);
        // 获得首地址信息
        this.address = ((sun.nio.ch.DirectBuffer) buffer).address();
    }

    /**
     * 获得chunk的buffer信息
    * 方法描述
    * @param needChunkSize
    * @return
    * @创建日期 2016年12月19日
    */
    public ByteBuffer getChunkItem(int needChunkSize) {
        // 如果当前不能加锁成功，则执行其他代码
        if (!isLock.compareAndSet(false, true)) {
            Thread.yield();
        }

        return null;
    }

}
