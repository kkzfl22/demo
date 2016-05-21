package com.liujun.thread.priority;

import java.util.concurrent.CountDownLatch;

public class FunMain
{
	public static void main(String[] args) throws InterruptedException
	{
		CountDownLatch count = new CountDownLatch(9);
		
		
		for (int i = 1; i <= 9; i++)
		{
			Thread th = new Thread(new FuncationInvike(count, "name:"+i));
			th.setPriority(i);
			
			if(i != 9)
			{
				th.start();
				count.countDown();
			}
		}
		
		Thread.sleep(1000);
		count.countDown();
		
	}
}
