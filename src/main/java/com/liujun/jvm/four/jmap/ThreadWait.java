package com.liujun.jvm.four.jmap;

public class ThreadWait {
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("开始..");
		Thread.currentThread().sleep(1000000);
		System.out.println("结束");
	}

}
