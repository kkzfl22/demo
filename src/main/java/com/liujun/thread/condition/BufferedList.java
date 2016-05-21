package com.liujun.thread.condition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用来使用Condition对象
 * @author Administrator
 *
 */
public class BufferedList<V> {
	
	/**
	 * 读写锁对象
	 */
	private Lock lock = new ReentrantLock();
	
	/**
	 * 当前作为放入的线程操作
	 */
	private Condition putCondition = lock.newCondition();
	
	/**
	 * 当前作为读取的线程操作
	 */
	private Condition readCondition = lock.newCondition();
	
	/**
	 * 当前缓冲区的大小
	 */
	private final int size = 10;
	
	/**
	 * 缓存区域
	 */
	private final Queue<V> CACHEVALUE= new LinkedList<V>();
	
	/**
	 * 进行写入操作
	 * @param put
	 * @throws InterruptedException
	 */
	public void put(V value)throws InterruptedException
	{
		//加锁处理
		lock.lock();
		try {
			//验证当前写入的缓冲区已经满，则需要让当前写入暂停
			while(CACHEVALUE.size() == size)
			{
				putCondition.await();
			}
			CACHEVALUE.offer(value);
			
			//通知读取线程可以来进行读取操作
			readCondition.signal();
		} 
		finally
		{
			//解锁处理
			lock.unlock();
		}
	}
	
	/**
	 * 从缓存队列中取出信息
	 * @return
	 * @throws InterruptedException
	 */
	public V get()throws InterruptedException
	{
		lock.lock();
		
		try
		{
			//进行检查，如果当前的队列已经为空，则让读取线程等待
			while(CACHEVALUE.isEmpty())
			{
				readCondition.await();
			}
			V value = CACHEVALUE.poll();
			
			//通知写入线程可以进行写入
			putCondition.signal();
			return value;
		}
		finally
		{
			lock.unlock();
		}
	}
	
	

}
