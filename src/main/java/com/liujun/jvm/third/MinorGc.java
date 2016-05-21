package com.liujun.jvm.third;

/**
 * 进行JVM垃圾收集相关的试验,
 * 在Serial / Serial Old模式下,对象在新生代Eden区中分配，当Eden区没有足够空间进行分配时，虚拟机将发起一次Minor GC
 * 
 * Minor GC （新生代GC)在新代生的垃圾收集动作
 * Major GC 发生在老年代的GC
 * @author liujun
 * @date 2015年8月2日
 * @verion 0.0.1
 */
public class MinorGc {
	
	private static final int _1MB = 1024*1024;
	
	/**
	 * VM 参数:
	 * -XX:+UseParallelGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * 
	 * 在运行时，通过-Xms20M -Xmx20M -Xmn10M 限制了java堆的大小为20Mb,不可扩展 
	 * 
	 * -XX:SurvivorRatio=8 决定了新生代中Eden区与一个Survivor区的空间比例是8：1
	 * 
	 * 
	 * -XX:+UseSerialGC 指定收集器为Serial + Serial Old的收集器组合
	 * 
	 * -XX:+UseParNewGC 指定使用ParNew + Serial Old的收集器组合进行内存回收
	 * 
	 * -XX:+UseConcMarkSweepGC 使用ParNew + CMS + Serial Old的收集器组合进行内存回收
	 * 
	 * -XX:+UseParallelGC 在server模式下，使用Parallel Scavenge + Serial Old模式进行收集(默认收集器)
	 * 
	 * -XX:+UseParallelOldGC 使用Parallel Scavenge + Paralled Old收集器进行收集
	 * 
	 * 
	 * 如果不指定垃圾收集器：默认收集器为:Parallel Scavenge 
	 */
	public static void testAllocation()
	{
		byte[] allocation1, allocation2,allocation3,allocation4;
		
		allocation1 = new byte[2*_1MB];
		allocation2 = new byte[2*_1MB];
		allocation3 = new byte[2*_1MB];
		allocation4 = new byte[4*_1MB];//出现一次Minor GC
	}
	
	public static void main(String[] args) {
		testAllocation();
	}

}
