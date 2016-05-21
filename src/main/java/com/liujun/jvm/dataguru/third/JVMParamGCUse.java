package com.liujun.jvm.dataguru.third;

import java.util.concurrent.locks.ReentrantLock;

public class JVMParamGCUse {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JVMParamGCUse jvm = new JVMParamGCUse();
		//jvm.useUseSerialGc();
		jvm.useUseParNewGc();
	}

	/**
	 * 使用printGc参数进行查看
	 * 
	 * -Xmx20m -Xms10m -XX:+PrintGC
	 * */
	public void useFunction() {

		for (int i = 0; i < 50; i++) {
			// 分配1M的空间
			byte[] lalue = new byte[1024 * 1024];
			System.out.print(lalue.length + "\t");
		}
	}

	/**
	 * 打印出GC的模式为使用Serial+Serial Old的收集器组合进行内存回收
	 * 
	 * -Xmx20m -Xms10M -XX:+PrintGCDetails -XX:+UseSerialGC
	 */
	public void useUseSerialGc()
	{
		this.useFunction();
	}
	
	/**
	 * 打印GCc收集日志信息,此模式下使用ParNew+Serial Old的收集器组合进行内存回收
	 * 
	 * -Xmx20m -Xms10M -XX:+PrintGCDetails -XX:+UseParNewGC
	 */
	public void useUseParNewGc()
	{
		this.useFunction();
	}
	
	
}
