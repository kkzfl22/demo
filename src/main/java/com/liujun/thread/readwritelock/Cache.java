package com.liujun.thread.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * doc:读写锁的应用示例，提供了缓存的的获取方法
 * 
 * @author liujun
 * @date 2018/07/21
 */
public class Cache {

	/**
	 * 实例对象
	 */
	public static final Cache INSTANCE = new Cache();

	/**
	 * 1 缓存的数据对象
	 */
	private Object data;

	/**
	 * 1,数据标识检查
	 */
	private volatile boolean valid;
	/**
	 * 读写锁对象
	 */
	private final ReentrantReadWriteLock RWL = new ReentrantReadWriteLock();

	public Object get(String key) {

		Object resultVal = null;

		// 首先开启读锁
		RWL.readLock().lock();
		try {
			// 首先檢查當前是否未被使用
			if (!valid) {
				// 当前不存在，则扩读锁
				RWL.readLock().unlock();
				// 进行写锁的获取
				RWL.writeLock().lock();
				try {
					// 再次进行标识检查，以防被后面的线程拿到写锁，多做一次数据更新操作
					if (!valid) {
						System.out.println("进入");
						// 摸拟从其他地方加载数据操作
						data = "load value";
						resultVal = data;
						valid = true;
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					RWL.readLock().lock();
				} finally {
					// 必须释放锁
					RWL.writeLock().unlock();
				}
			}
			resultVal = data;

		} finally {
			RWL.readLock().unlock();
		}

		return resultVal;
	}

	public static void main(String[] args) {
		Runnable run = new Runnable() {
			public void run() {
				String value = (String) Cache.INSTANCE.get("123");

				System.out.println("获取数据:" + value);
			}
		};

		for (int i = 0; i < 5; i++) {
			new Thread(run).start();
		}
	}

}
