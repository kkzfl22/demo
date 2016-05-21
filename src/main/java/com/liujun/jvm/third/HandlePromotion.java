package com.liujun.jvm.third;

/**
 * 进行JVM试验空间分配担保
 * 
 * 在发生Minor GC之前，虚拟机会先检查老年代最大可用的连续内存空间是否大于新生代所有对象总空间，如果这个条件成立，那么Minor GC可以确保是安全的
 * 
 * HandlePromotionFailure=true，在jdk6 update 24之后，HandlePromotionFailure参数不会影响虚拟机的空间担保策略
 * 本测试使用jdk7，以及jdk1.6-45版本，HandlePromotionFailure将不针对空间担保产生影响
 * @author liujun
 * @date 2015年8月4日
 * @verion 0.0.1
 */
public class HandlePromotion {
	
	private static final int _1MB = 1024 * 1024;
	
	/**
	 * Vm参数 -XX:+UseParallelGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:-HandlePromotionFailure
	 */
	public static void testHandlePromotion()
	{
		byte[] allocation1,allocation2,allocation3,allocation4,allocation5,allocation6,allocation7;
		
		allocation1 = new byte[_1MB * 2];
		allocation2 = new byte[_1MB * 2];
		allocation3 = new byte[_1MB * 2];
		allocation1 = null;
		
		allocation4 = new byte[_1MB * 2];
		allocation5 = new byte[_1MB * 2];
		allocation6 = new byte[_1MB * 2];
		allocation4 = null;
		allocation5 = null;
		allocation6 = null;
		allocation7 = new byte[_1MB * 2];
	}

	public static void main(String[] args) {
		testHandlePromotion();
	}
}
