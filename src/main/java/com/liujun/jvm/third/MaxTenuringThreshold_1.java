package com.liujun.jvm.third;

/**
 * 试验长期存活的对象将进入老年代
 * @author liujun
 * @date 2015年8月3日
 * @verion 0.0.1
 */
public class MaxTenuringThreshold_1 {

	private static final int _1MB = 1024 * 1024;
	
	/**
	 * Vm 参数:-XX:+UseSerialGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
	 *
	 *MaxTenuringThreshold=1 设置晋升老年代的年龄阈值
	 *
	 *
	 *-XX:+UseSerialGC 使用Serial + Serial Old组合进行内存回收
	 *
	 */
	public static void testTenuringThreshold()
	{
		byte[] allocation1,allocation2,allocation3;
		
		allocation1 = new byte[_1MB / 4];
		//什么时候进入老年代取决于xx:MaxTenuringThreshold设置
		allocation2 = new byte[_1MB * 4];
		allocation3 = new byte[_1MB * 4];
		allocation3 = null;
		//在进行此对象分配时，发现内存已经不足，将会进行一次GC回收，将之前的allocation1放入到老年代中
		allocation3 = new byte[_1MB * 4];
	}
	
	
	public static void main(String[] args) {
		testTenuringThreshold();
	}
}
