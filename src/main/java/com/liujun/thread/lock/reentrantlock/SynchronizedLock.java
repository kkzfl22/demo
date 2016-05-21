package com.liujun.thread.lock.reentrantlock;

/**
 * 可重入锁synchronized锁
 * @author liujun
 *
 */
public class SynchronizedLock implements Runnable
{
	public synchronized void get()
	{
		System.out.println("get:" + Thread.currentThread().getId());
		set();
	}

	public synchronized void set()
	{
		System.out.println("set:" + Thread.currentThread().getId());
		
		System.out.println("---------");
	}

	@Override
	public void run()
	{
		get();
	}

	public static void main(String[] args)
	{
		SynchronizedLock lock = new SynchronizedLock();
		
		new Thread(lock).start();
		new Thread(lock).start();
		new Thread(lock).start();
		new Thread(lock).start();

	}

}
