package com.liujun.jvm.third;

/**
 * 动态对象年龄判定
 * 
 * allocation1 + allocation2 已经达到了512K，是同年的，满足同年对象达到Survivor空间的一半的规则
 * @author liujun
 * @date 2015年8月3日
 * @verion 0.0.1
 */
public class MaxTenuringThreshold_15_2 {

	private static final int _1MB = 1024 * 1024;
	
	/**
	 * Vm 参数:-XX:+UseSerialGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
	 *
	 *MaxTenuringThreshold=1 设置晋升老年代的年龄阈值
	 *
	 *当MaxTenuringThreshold = 15时，第二次Gc发生后，allaction对象则留在新生代Survivor空间，这时新生代仍然占用404K
	 *
	 *
	 *-XX:+UseSerialGC 使用Serial + Serial Old组合进行内存回收
	 *
	 */
	public static void testTenuringThreshold()
	{
		byte[] allocation1,allocation2,allocation3,allocation4;
		
		allocation1 = new byte[_1MB / 4];
		//allocation1+alloction2大于Survivo空间的一半
		allocation2 = new byte[_1MB / 4];
		allocation3 = new byte[_1MB * 4];
		allocation3 = new byte[_1MB * 4];
		allocation4 = new byte[_1MB * 4];
		allocation4 = null;
		allocation4 = new byte[_1MB * 4];
	}
	
	
	public static void main(String[] args) {
		testTenuringThreshold();
	}
}
