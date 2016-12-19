package com.liujun.memory;

import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.concurrent.atomic.AtomicBoolean;

import sun.nio.ch.DirectBuffer;

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
     * 可以使用的chunkNum
    * @字段说明 useMemoryChunkNum
    */
    private int canUseChunkNum;

    // /**
    // * 记录内存地址信息
    // * @字段说明 address
    // */
    // private final long address;

    public PageMemory(ByteBuffer buffer, int chunkSize) {
        this.buffer = buffer;

        // 设置chunk的大小
        this.chunkSize = chunkSize;
        // 设置chunk的数量
        this.chunkCount = this.buffer.capacity() / this.chunkSize;
        // 设置当前内存标识块的大小
        this.memUseSet = new BitSet(this.chunkCount);
        // 默认可使用的chunk数量为总的chunk数
        this.canUseChunkNum = chunkCount;
        // // 获得首地址信息
        // this.address = ((sun.nio.ch.DirectBuffer) buffer).address();
    }

    /**
     * 检查当前内存页能否满足内存数据的分配要求
    * 方法描述
    * @param chunkNum
    * @return 1,可分配 0，不能
    * @创建日期 2016年12月19日
    */
    public int checkNeedChunk(int chunkNum) {
        // 1,检查当前容量是否已经满
        if (this.canUseChunkNum == 0) {
            return MemonryConsole.FULL.getFlag();
        } else {
            // 如果当前可分配的内存块满足要求
            if (this.canUseChunkNum >= chunkNum) {
                return MemonryConsole.CANUSE.getFlag();
            }
            // 如果当前的可分配内存不够
            else {
                return MemonryConsole.NOT_ENOUGH.getFlag();
            }
        }
    }

    /**
     * 获得chunk的buffer信息
    * 方法描述
    * @param needChunkSize
    * @return
    * @创建日期 2016年12月19日
    */
    public ByteBuffer alloactionMemory(int needChunkSize) {
        // 如果当前的可分配的内在块小于需要内存块，则返回
        if (canUseChunkNum < needChunkSize) {
            return null;
        }

        // 如果当前不能加锁成功，则返回为null
        if (!isLock.compareAndSet(false, true)) {
            return null;
        }

        try {

            int startIndex = -1;
            int endIndex = 0;
            // 找到当前连续未使用的内存块
            for (int i = 0; i < chunkCount; i++) {
                if (memUseSet.get(i) == false) {
                    if (startIndex == -1) {
                        startIndex = i;
                        endIndex = 1;
                    } else {
                        if (++endIndex == needChunkSize) {
                            break;
                        }
                    }
                }
                // 如果已经使用，则标识当前已经使用，则从已经使用的位置开始
                else {
                    startIndex = -1;
                    endIndex = 0;
                }
            }

            // 如果找到适合的内存块大小
            if (endIndex == needChunkSize) {
                // 将这一批数据标识为已经使用
                memUseSet.set(startIndex, startIndex + needChunkSize);
                //
                buffer.position(startIndex * chunkSize);
                buffer.limit((startIndex + needChunkSize) * chunkSize);
                ByteBuffer bufferReslult = buffer.slice();

                // 当前可使用的，为之前的结果前去当前的需要的，
                canUseChunkNum = canUseChunkNum - needChunkSize;

                return bufferReslult;

            } else {
                return null;
            }

        } finally {
            isLock.set(false);
        }
    }

    /**
     * 进行内存的归还操作，以便后面再使用
    * 方法描述
    * @param parentBuffer
    * @param chunkStart
    * @param chunkNum
    * @创建日期 2016年12月19日
    */
    public void recycleBuffer(ByteBuffer parentBuffer, int chunkStart,
            int chunkNum) {
        if (this.buffer == parentBuffer) {
            // 如果加锁失败，则执行其他代码
            if (!isLock.compareAndSet(false, true)) {
                Thread.yield();
            }

            try {
                // 将当前指定的内存块归还
                memUseSet.clear(chunkStart, (chunkStart + chunkNum));

                // 归还了内存，则需要将可使用的内存加上归还的内存
                this.canUseChunkNum = canUseChunkNum + chunkNum;

            } finally {
                isLock.set(false);
            }

        }
    }

    @SuppressWarnings("restriction")
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(2048);

        PageMemory page = new PageMemory(buffer, 256);

        ByteBuffer bufferItem = page.alloactionMemory(2);
        ByteBuffer bufferItem2 = page.alloactionMemory(2);
        ByteBuffer bufferItem3 = page.alloactionMemory(2);

        // 获得内存buffer
        sun.nio.ch.DirectBuffer thisNavBuf = (DirectBuffer) bufferItem2;
        // attachment对象在buf.slice();的时候将attachment对象设置为总的buff对象
        sun.nio.ch.DirectBuffer parentBuf = (DirectBuffer) thisNavBuf
                .attachment();
        // 已经使用的地址减去父类最开始的地址，即为所有已经使用的地址，除以chunkSize得到chunk当前开始的地址,得到整块内存开始的地址
        int startChunk = (int) ((thisNavBuf.address() - parentBuf.address())
                / 256);

        // 归还当前buffer2的内存
        page.recycleBuffer((ByteBuffer) parentBuf, startChunk, 2);

        System.out.println(bufferItem);
        System.out.println(bufferItem2);
        System.out.println(bufferItem3);

    }

}
