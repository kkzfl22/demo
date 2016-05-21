package com.liujun.jvm.four.jconsole;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置-Xms100m -Xmx100m -XX:+UseSerialGC
 * 
 * -Xms 初始Heap大小
 * -Xmx java heap最大值
 * -Xmn young generation的heap大小
 * @author liujun
 * @date 2015年8月9日
 * @verion 0.0.1
 */
public class OOMConsole {
	
	static class OOMObject{
		public byte[] placeholder = new byte[64*1024];
	}
	
	public static void fillHeap(int num) throws InterruptedException
	{
		List<OOMObject> list = new ArrayList<OOMConsole.OOMObject>();
		for (int i = 0; i < num; i++) {
			//稍作延迟
			Thread.sleep(50);
			list.add(new OOMObject());
		}
		//由于system.gc在此filheap方法中，所以，此方法的中的对旬未被回收
		//System.gc();
	}

	public static void main(String[] args) throws InterruptedException {
		
		Thread.currentThread().sleep(10000);
		
		System.out.println("start ....");
		
		fillHeap(1000);
		
		System.out.println("end ....");
		//当前在方法区之外，执行了内存回收，所以所有的内存都被回收
		System.gc();
		System.out.println("内存回收完成");
		
		Thread.currentThread().sleep(10000000);
	}
}
