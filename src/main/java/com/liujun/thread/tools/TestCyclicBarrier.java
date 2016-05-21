package com.liujun.thread.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestCyclicBarrier
{
	/**
	 * 检查数据主叫写入完成的标识
	 */
	private static CyclicBarrier waitBarrier_all = new CyclicBarrier(2);

	/**
	 * 获得线程对象
	 * 
	 * @param waitBarrier
	 * @return
	 */
	public CycRun getRunable(CyclicBarrier waitBarrier)
	{
		return new CycRun(waitBarrier);
	}

	public static void main(String[] args) throws InterruptedException, BrokenBarrierException, TimeoutException
	{
		TestCyclicBarrier runAble = new TestCyclicBarrier();

		CycRun cyncRun1 = runAble.getRunable(waitBarrier_all);

		Thread run1 = new Thread(cyncRun1);

		run1.start();

		while (true)
		{
			// 进行场景模拟
			// 1,当前处理运行的线程中出现了问题，没有开始,但其获取线程已经开始运行
			cyncRun1.setIsRun(false);

			// 等待2秒
			try
			{
				waitBarrier_all.await(5, TimeUnit.SECONDS);
			} catch (Exception e)
			{
				e.printStackTrace();
			}

			// 重置
			waitBarrier_all.reset();
			
			System.out.println("运行操作完成");

			// 后面问题恢复，开始运行
			cyncRun1.setIsRun(true);

			// 等待2秒
			try
			{
				waitBarrier_all.await(5, TimeUnit.SECONDS);
			} catch (Exception e)
			{
				e.printStackTrace();
			}

			// 重置
			waitBarrier_all.reset();
		}
	}

	public class CycRun implements Runnable
	{
		private final CyclicBarrier waitBarrier;

		/**
		 * 是否运行
		 */
		private AtomicBoolean isRun = new AtomicBoolean(false);

		public CycRun(CyclicBarrier waitBarrier)
		{
			this.waitBarrier = waitBarrier;
		}

		public boolean getIsRun()
		{
			return isRun.get();
		}

		public void setIsRun(boolean isRun)
		{
			this.isRun.set(isRun);
		}

		@SuppressWarnings("static-access")
		@Override
		public void run()
		{
			while (true)
			{
				System.out.println("当前线程中进入正在运行");
				try
				{
					Thread.currentThread().sleep(5000);


					// 如果当前处理运行状态
					if (isRun.get())
					{
						System.out.println("线程中等待开始运行");
						// 进行等待调用
						waitBarrier.await();
					}

				} catch (InterruptedException e)
				{
					e.printStackTrace();
				} catch (BrokenBarrierException e)
				{
					System.out.println("线程中发生异常");
					e.printStackTrace();
				}

			}
		}

	}
}
