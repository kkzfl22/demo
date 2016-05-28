package com.liujun.jvm.dataguru.four;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.liujun.util.NumberSeq;

public class OldStw {

    /**
     * 测试大对象直接放入老年代信息
     * PretenureSizeThreshold参数只对Serial和ParNew两款收集器有效
     * 
     * -Xmx100m -Xms100m -Xmn20m  -XX:SurvivorRatio=8  -XX:PretenureSizeThreshold=5242880 -XX:+PrintGCDetails -XX:+PrintHeapAtGC -XX:+UseSerialGC
     * 
     * jstat -gcutil  8052 10000
     * 
     * @param args
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Map<Integer, byte[]> map = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入开始进行：");
        String line = scanner.nextLine();
        System.out.println("结果输出:" + line);

        // 分配10M
        map.put(NumberSeq.getnewInstance().getCurrSeqValue(), new byte[1024 * 1024 * 6]);

        Scanner scanner2 = new Scanner(System.in);
        System.out.println("请输入开始进行：");
        String line2 = scanner2.nextLine();

        System.out.println("结果输出2:" + line2);

    }
}
