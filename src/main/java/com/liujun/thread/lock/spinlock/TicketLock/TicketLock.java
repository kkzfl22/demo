package com.liujun.thread.lock.spinlock.TicketLock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * 自旋锁的一种
 * 
 * Ticket锁主要解决的是访问顺序的问题，主要的问题是在多核cpu上
 * @author liujun
 *
 */
public enum TicketLock {
	
	INANCE();
	
	private TicketLock()
	{
		
	}
	
	
	
	private AtomicInteger serviceNum = new AtomicInteger();
	
	private AtomicInteger ticketNum = new AtomicInteger();
	
	private static final ThreadLocal<Integer> LOCAL = new ThreadLocal<Integer>();
	
	public void lock()
	{
		int ticket = ticketNum.getAndIncrement();
		LOCAL.set(ticket);
		while(ticket != serviceNum.get())
		{
		}
	}
	
	public void unlock()
	{
		int myticket = LOCAL.get();
		serviceNum.compareAndSet(myticket, myticket+1);
	}

}
