package com.liujun.jvm.dataguru.four;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.liujun.util.NumberSeq;

/**
 * 进行老年代GC的标识与清除算法的研究
 * @author liujun
 * @date 2016年5月28日
 * @verion 0.0.1
 */
public class OldFullGcTest {

    /**
     * 设置gc信息
     * -XX:+PrintHeapAtGC
     * 
     * -Xmx1025m -Xms1025m -Xmn1m  -Xloggc:log-fullgc.log -XX:+PrintGCDetails -XX:+UseSerialGC  -XX:SurvivorRatio=8  -XX:PretenureSizeThreshold=1023
     * 
     * @param args
     */
    public static void main(String[] args) {
        OldFullGcTest fullGc = new OldFullGcTest();
        Thread fullGcThread = fullGc.getFullGc();
        Thread printTimeThread = fullGc.getPrintTime();

        Scanner scan = new Scanner(System.in);
        System.out.println("请输入开始:");
        String line = scan.nextLine();

        System.out.println("开始:" + line);
        fullGcThread.start();
        printTimeThread.start();

    }

    /**
     * 得到打印时间对象
     * @return
     */
    public Thread getPrintTime() {
        return new Thread(new PrintTime());
    }

    /**
     * 得到一个释放GC的对象
     * @return
     */
    public Thread getFullGc() {
        return new Thread(new OldFullGc());
    }

    /**
     * 运行进行时间打印
     * 
     * @author liujun
     * @date 2016年5月28日
     * @verion 0.0.1
     */
    class PrintTime implements Runnable {
        public void run() {
            long start = System.currentTimeMillis();

            while (true) {
                long end = System.currentTimeMillis();

                System.out.println("时间差:" + (end - start) / 1000f);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * 测试老年代的 GC
     * @author liujun
     * @date 2016年5月28日
     * @verion 0.0.1
     */
    class OldFullGc implements Runnable {

        Map<Integer, byte[]> map = new HashMap<>();

        @Override
        public void run() {
            while (true) {
                // 如果当前对象内老年代的内存大小如果到达900M, 则进行清除
                if (map.size() / 1024 >= 900) {
                    // 进行消息的清除
                    map.clear();
                    System.out.println("---进行内存空间的释放...");
                }
                // 每次分配1M的空间
                for (int i = 0; i < 1024; i++) {
                    map.put(NumberSeq.getnewInstance().nextSeqValue(), new byte[1024]);
                }

            }
        }

    }
}
