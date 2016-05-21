package com.liujun.thread.lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * 
 * 自旋锁是采用让当前线程不停地的在循环体内执行实现的，当循环的条件被其他线程改变时 才能进入临界区
 * 
 * 由于自旋锁只是将当前线程不停地执行循环体，不进行线程状态的改变，所以响应速度更快
 * 
 * 当线程数不停增加时，性能下降明显，因为每个线程都需要执行，占用CPU时间。
 * 如果线程竞争不激烈，并且保持锁的时间段。适合使用自旋锁
 * 
 * @author liujun
 *
 */
public enum SpinLock {
	
	Instance;
	
	private SpinLock()
	{
		
	}
	
	private AtomicReference<Thread> lock = new AtomicReference<Thread>();
	
	/**
	 * 当第一个线程以原子的方式修改lock的引用后，就可以继续执行
	 * 
	 * 当第二个线程过来更新lock时，因为当前的引用已经不为nll，所以循环一直就可以继续执行,
	 * 
	 * 这样就做到了，独占
	 * 
	 */
	public void lock()
	{
		Thread curr = Thread.currentThread();
		
		while(!lock.compareAndSet(null, curr))
		{
			
		}
	}
	
	public void unlock()
	{
		Thread curr =Thread.currentThread();
		
		lock.compareAndSet(curr, null);
	}

}
