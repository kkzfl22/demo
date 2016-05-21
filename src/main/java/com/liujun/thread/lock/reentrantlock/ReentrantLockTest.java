package com.liujun.thread.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest implements Runnable
{
	private ReentrantLock lock = new ReentrantLock();
	
	public void get()
	{
		lock.lock();
		System.out.println("get:"+Thread.currentThread().getId());
		set();
		lock.unlock();
	}
	
	public void set()
	{
		lock.lock();
		System.out.println("set:"+Thread.currentThread().getId());
		System.out.println("............");
		lock.unlock();
	}
	

	@Override
	public void run()
	{
		get();
	}
	
	public static void main(String[] args)
	{
		ReentrantLockTest test = new ReentrantLockTest();
		
		new Thread(test).start();
		new Thread(test).start();
		new Thread(test).start();
		new Thread(test).start();
	}

}
