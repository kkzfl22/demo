package com.liujun.jvm.dataguru.four;

import java.util.HashMap;
import java.util.Map;

import com.liujun.util.NumberSeq;

public class GcStw {
    /**
     * 最大为50M内存
     */
    private static final int MAX_MEMORY = 450;

    /**
     * 设置运行参数-Xmx512M -Xms512M -Xmn1M -XX:+PrintGCDetails -XX:+UseSerialGC  -XX:PretenureSizeThreshold=50 -XX:MaxTenuringThreshold=1 -Xloggc:gc-stw.log
     * 
     * @param args
     */
    public static void main(String[] args) {
        GcStw gcstw = new GcStw();
        gcstw.runPrint();
        gcstw.runGetMemory();

    }

    /**
     * 运行进行时间打印
     * 
     * @author liujun
     * @date 2016年5月28日
     * @verion 0.0.1
     */
    class PrintTime implements Runnable {
        public final long STARTTIME = System.currentTimeMillis();

        public void run() {
            while (true) {
                long t = System.currentTimeMillis() - STARTTIME;

                System.out.println("时间差:" + t / 1000f);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 进行输据的输出
     */
    public void runPrint() {
        new Thread(new PrintTime()).start();
    }

    /**
     * 进行内存输入，以达到进行GC的条件
     */
    public void runGetMemory() {
        new Thread(new getMomory()).start();
    }

    /**
     * 进行不断的内存获取，以达到出现GC现象
     * 
     * @author liujun
     * @date 2016年5月28日
     * @verion 0.0.1
     */
    class getMomory implements Runnable {

        Map<Integer, byte[]> memonryMap = new HashMap<Integer, byte[]>();

        @Override
        public void run() {
            try {
                // 首先进行检查内存是否已经达到回收的条件
                while (true) {
                    if (memonryMap.size() * 2048 / 1024 / 1024 >= MAX_MEMORY) {
                        System.out.println("进行清理中....");
                        // 进行内存清理
                        memonryMap.clear();
                    }

                    // for (int i = 0; i < 1024; i++)
                    // {
                    // 进行数据放入
                    memonryMap.put(NumberSeq.getnewInstance().nextSeqValue(), new byte[1024 * 1024 * 2]);
                    // }

                    // 进行十分之一秒的暂停

                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
