package com.liujun.thread.priority;

import java.util.concurrent.CountDownLatch;

public class FuncationInvike implements Runnable
{
	/**
	 * 进行控制开始
	 */
	private CountDownLatch dount;

	/**
	 * 当前操作
	 */
	private String name;

	public FuncationInvike(CountDownLatch dount, String name)
	{
		this.dount = dount;
		this.name = name;
	}

	@Override
	public void run()
	{
		System.out.println("线程创建");
		try
		{
			dount.await();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		System.out.println("当前运行:" + name);
	}
}
