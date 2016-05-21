package com.liujun.io.bio.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServerHandlerExecutePool {

	private ExecutorService executor;

	public TimeServerHandlerExecutePool(int maxpoolSize, int queueSize) {
		//创建线程池对象
		executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxpoolSize, 120l, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(
				queueSize));
	}
	
	//向线程池中添加一个任务
	public void execute(Runnable task)
	{
		executor.execute(task);
	}
	

}
