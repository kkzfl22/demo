package com.liujun.thread.lock.spinlock.clhlock;

public class TestCLHLock {

	public static void main(String[] args) {
		TestCLHLock task = new TestCLHLock();

		new Thread(task.getRun()).start();
		new Thread(task.getRun()).start();
		new Thread(task.getRun()).start();
		new Thread(task.getRun()).start();
		new Thread(task.getRun()).start();
		// new Thread(task.getRun()).start();
		// new Thread(task.getRun()).start();

	}

	public Runnable getRun() {
		Runnable run = new Runnable() {

			@SuppressWarnings("static-access")
			@Override
			public void run() {

				CLHLock.Instance.lock();
				System.out.println("当前执行" + Thread.currentThread().getId());

				try {
					Thread.currentThread().sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				CLHLock.Instance.unlock();

			}
		};

		return run;
	}
}
