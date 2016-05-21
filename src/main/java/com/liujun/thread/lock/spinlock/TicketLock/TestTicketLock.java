package com.liujun.thread.lock.spinlock.TicketLock;

public class TestTicketLock {

	public static void main(String[] args) {
		TestTicketLock task = new TestTicketLock();
		
		 new Thread(task.getRun()).start();
		 new Thread(task.getRun()).start();
		new Thread(task.getRun()).start();
		new Thread(task.getRun()).start();
		new Thread(task.getRun()).start();
		new Thread(task.getRun()).start();
		new Thread(task.getRun()).start();

	}

	
	public Runnable getRun()
	{
		Runnable run = new Runnable() {

			@SuppressWarnings("static-access")
			@Override
			public void run() {
				TicketLock.INANCE.lock();
				System.out.println("当前执行" + Thread.currentThread().getId());

				try {
					Thread.currentThread().sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				TicketLock.INANCE.unlock();

			}
		};
		
		return run;
	}
}
