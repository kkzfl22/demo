package com.liujun.thread.lock.spinlock;

public class TestSpinLock {

	public static void main(String[] args) {
		TestSpinLock task = new TestSpinLock();
		
		 new Thread(task.getRun()).start();
		 new Thread(task.getRun()).start();
		// new Thread(task.getRun()).start();
		// new Thread(task.getRun()).start();
		// new Thread(task.getRun()).start();
		// new Thread(task.getRun()).start();
		// new Thread(task.getRun()).start();

	}

	
	public Runnable getRun()
	{
		Runnable run = new Runnable() {

			@SuppressWarnings("static-access")
			@Override
			public void run() {
				
				
				SpinLock.Instance.lock();
				System.out.println("当前执行" + Thread.currentThread().getId());

				try {
					Thread.currentThread().sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				SpinLock.Instance.unlock();

			}
		};
		
		return run;
	}
}
