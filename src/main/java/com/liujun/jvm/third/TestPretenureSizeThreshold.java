package com.liujun.jvm.third;

/**
 * 大对旬直接进入老年代
 * 虚拟机
 * @author liujun
 * @date 2015年8月2日
 * @verion 0.0.1
 */
public class TestPretenureSizeThreshold {
	
	
	private static final  int _1MB = 1024*1024;
	
	/**
	 * VM 参数:
	 * 
	 * -XX:+UseSerialGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
	 * 
	 * -XX:PretenureSizeThreshold 令大于这个设置值 的对象直接在老年代分配
	 *  参数只对Serial 和 ParNew 两款收集器有效 
	 * Parallel Scavenge 收集器不认识这个参数
	 * 
	 * -XX:+UseSerialGC使用Serial + Serial Old的收集器组合
	 * 
	 * -XX:+UseParNewGC 使用ParNew + Serial Old的收集器
	 */
	public static void testPretenureSizeThreshold()
	{
		byte[] allocation;
		
		allocation = new byte[4*_1MB];
	}
	
	public static void main(String[] args) {
		testPretenureSizeThreshold();
	}

}
