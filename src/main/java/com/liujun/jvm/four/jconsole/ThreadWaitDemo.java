package com.liujun.jvm.four.jconsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 演示一个正在等待的活锁
 * @author liujun
 * @date 2015年8月9日
 * @verion 0.0.1
 */
public class ThreadWaitDemo {
	
	/**
	 * 线程死循环演示
	 */
	public static void createBusyTHread()
	{
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true)
				{
					;
				}
			}
		},"testBusyThread");
		
		thread.start();
	}
	
	/**
	 * 线程锁等待演示
	 * @param lock
	 */
	public static void createLockThread(final Object lock)
	{
		Thread thread  = new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (lock) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		},"TestLockThread");
		
		thread.start();
	}
	
	public static void main(String[] args) throws IOException {
		
		//1，等待控制台输入
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		//开始线程进行死循环执行
		createBusyTHread();
		br.readLine();
		Object obj = new Object();
		//使当前对象一直处于等待状态
		createLockThread(obj);
	}

}
