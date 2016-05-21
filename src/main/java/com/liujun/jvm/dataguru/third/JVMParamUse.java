package com.liujun.jvm.dataguru.third;

import java.util.ArrayList;
import java.util.List;

public class JVMParamUse {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JVMParamUse jvm = new JVMParamUse();
		// jvm.usePrintGc();
		// jvm.usePrintGcDetails();
		// jvm.usePrintGcTimeStamps();
		//jvm.usePrintClassHistogram();
		//jvm.useMemParm();
		//jvm.useHeapDumpOnOutOfMemoryError();
		//jvm.useOnOutOfMemoryError();
		//jvm.useJvmPrintCommandLineFlags();
		//jvm.useJvmPrintFlagsFinal();
		//jvm.useModelServer();
		jvm.useModelClient();
	}

	/**
	 * 使用printGc参数进行查看
	 * 
	 * -Xmx20m -Xms10m -XX:+PrintGC
	 * */
	public void usePrintGc() {

		for (int i = 0; i < 50; i++) {
			// 分配1M的空间
			byte[] lalue = new byte[1024 * 1024];
			System.out.print(lalue.length + "\t");
		}
	}

	/**
	 * 使用printGcDetails打印GC详细信息
	 * 
	 * -Xmx20m -Xms10m -XX:+PrintGCDetails
	 */
	public void usePrintGcDetails() {
		this.usePrintGc();
	}

	/**
	 * 使用printGcTimeStamps打印GC时间戳信息 ,目前此打印不出来任何信息
	 * 
	 * -Xmx20m -Xms10m -XX:+PrintGCTimeStamps
	 */
	public void usePrintGcTimeStamps() {
		this.usePrintGc();
	}

	/**
	 * 以文件形式输出gc信息
	 * 
	 * -Xmx20m -Xms10m -XX:+PrintGCDetails -Xloggc:gc-ref.log
	 */
	public void useloggc() {
		this.useloggc();
	}

	/**
	 * 在每次gc后都打印堆的信息 -Xmx20m -Xms10m -XX:+PrintHeapAtGC
	 */
	public void usePrintHeapAtGC() {
		this.useloggc();
	}

	/**
	 * 类加载监控 -Xmx20m -Xms10m -XX:+TraceClassLoading 
	 */
	public void useTraceClassLoading() {
		this.useloggc();
	}

	/**
	 * 按下Ctrl+Break(中断键)后，打印类的信息： 
	 * 
	 * -Xmx20m -Xms10m -XX:+PrintClassHistogram
	 */
	public void usePrintClassHistogram() {
		this.usePrintGc();
		new Thread(new Runnable() {

			@SuppressWarnings("static-access")
			@Override
			public void run() {
				try {
					Thread.currentThread().sleep(100000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}
	
	/**
	 * 进行内存控制参数设置 
	 * 
	 * -Xmx30m -Xms10m -XX:NewRatio=1 -XX:SurvivorRatio=4  -XX:+PrintHeapAtGC 
	 */
	public void useMemParm()
	{
		this.usePrintGc();
	}
	
	/**
	 * 当发生内存溢出时导出到文件
	 * 
	 * -Xmx20m -Xms10m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=outofmemoryerror.dump
	 */
	public void useHeapDumpOnOutOfMemoryError()
	{
		List<byte[]> list = new ArrayList<byte[]>();
		
		for (int i = 0; i < 100; i++) {
			//每次向集体中添加1M的数据
			list.add(new byte[1024*1024]);
		}
	}
	
	
	/**
	 * 当发生内存溢出时执行文件
	 * 
	 * -Xmx20m -Xms10m "-XX:OnOutOfMemoryError=D:\java\test\stack.bat %p"
	 */
	public void useOnOutOfMemoryError()
	{
		List<byte[]> list = new ArrayList<byte[]>();
		
		for (int i = 0; i < 100; i++) {
			//每次向集体中添加1M的数据
			list.add(new byte[1024*1024]);
		}
	}
	
	/**
	 * 打印出GC的选项信息
	 * 
	 * -Xmx20m -Xms10M -XX:+PrintGCDetails -XX:+PrintVMOptions
	 */
	public void useJvmOptions()
	{
		this.usePrintGc();
	}
	
	/**
	 * 打印出JVM的当前选项信息
	 * 
	 * -Xmx20m -Xms10M -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
	 */
	public void useJvmPrintCommandLineFlags()
	{
		this.usePrintGc();
	}
	
	/**
	 * 打印出JVM的全部支持信息
	 * 
	 * -Xmx20m -Xms10M -XX:+PrintGCDetails  -XX:+PrintFlagsFinal
	 */
	public void useJvmPrintFlagsFinal()
	{
		this.usePrintGc();
	}
	
	
	/**
	 * 打印出JVM为server模式
	 * 
	 * -Xmx20m -Xms10M -XX:+PrintGCDetails -server
	 */
	public void useModelServer()
	{
		this.usePrintGc();
	}
	/**
	 * 打印出JVM的模式为client
	 * 
	 * -Xmx20m -Xms10M -XX:+PrintGCDetails -client
	 */
	public void useModelClient()
	{
		this.usePrintGc();
	}
	/**
	 * 打印出JVM的模式为client
	 * 
	 * -Xmx20m -Xms10M -XX:+PrintGCDetails -XX:+UseSerialGC
	 */
	public void useUseSerialGc()
	{
		this.usePrintGc();
	}
	
	
}
