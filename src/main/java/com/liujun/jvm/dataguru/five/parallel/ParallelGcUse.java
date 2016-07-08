package com.liujun.jvm.dataguru.five.parallel;

import java.util.ArrayList;
import java.util.List;

/**
 * 针对Paralled这个收集器进行使用
 * @author liujun
 * @date 2016年6月5日
 * @verion 0.0.1
 */
public class ParallelGcUse {

    private static final int MAX_SIZE = 50;

    public static void main(String[] args) {
        ParallelGcUse paral = new ParallelGcUse();
        // paral.useMemony();
        // paral.useUseParallelOldGC();
        paral.useUseSerialGC();
    }

    /**
     * 进行内存的使用
     */
    @SuppressWarnings("static-access")
    public void useMemony() {
        List<byte[]> list = new ArrayList<byte[]>();
        for (int i = 0; i < MAX_SIZE; i++) {
            // 每次分配1M的空间
            list.add(new byte[1024 * 1024]);

            if (i % 3 == 0) {
                list.clear();
            }

            try {
                Thread.currentThread().sleep(300l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 进行使用Parallel收集器+ 老年代串行
     * 
     * -Xmx10m -Xms5m -XX:+UseParallelGC -XX:+PrintGCDetails
     * 
     */
    public void useParallelGC() {
        useMemony();
    }

    /**
     * 进行使用Parallel收集器+ 并行老年代
     * 
     * -Xmx10m -Xms5m -XX:+UseParallelOldGC -XX:+PrintGCDetails
     * 
     */
    public void useUseParallelOldGC() {
        useMemony();
    }

    /**
     * 进行使用Parallel收集器
     * -XX:MaxGCPauseMills=100
     *   -XX:GCTimeRatio=2 
     * -Xmx10m -Xms5m -XX:+UseParallelOldGC -XX:MaxGCPauseMillis=2 -XX:+PrintGCDetails
     * 
     */
    public void useUseSerialGC() {
        useMemony();
    }

    /**
     * 使用cms收集器参数的使用
     * 
     *  -Xmx10m -Xms5m -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails
     */
    public void useConcMarkSweepGC() {
        useMemony();
    }

}
