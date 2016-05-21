package com.liujun.thread.threadlocal;

import com.liujun.thread.threadlocal.util.ThreadUtils;

/**
 * 验证多线程间ThreadLocal的数据是互不干扰的
 * @author liujun
 *
 */
public class ThreadLocalTestChr1
{
	
	public static void main(String[] args)
	{
		ThreadLocalTestChr1 char2 = new ThreadLocalTestChr1();
		char2.testchr1();
	}
	
	public void testchr1()
	{
		Thread thr1 = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				System.out.println(Thread.currentThread().getId() + ":初始值:"+ThreadUtils.getData());
				ThreadUtils.setData("data1");
				ThreadSleep(500);
				System.out.println(Thread.currentThread().getId() + ":修改后的:"+ThreadUtils.getData());
			}
		});	
		
		Thread thr2 = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				System.out.println(Thread.currentThread().getId() + ":初始值:"+ThreadUtils.getData());
				ThreadUtils.setData("data2");
				ThreadSleep(501);
				System.out.println(Thread.currentThread().getId() + ":修改后的:"+ThreadUtils.getData());
			}
		});	
		
		thr1.start();
		thr2.start();
	}
	
	
	@SuppressWarnings("static-access")
	public static void ThreadSleep(long times)
	{
		try
		{
			Thread.sleep(times);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
