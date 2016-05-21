package com.liujun.thread.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 构造通用的结果缓存技术
 * 
 * @author Administrator
 * 
 * @param <A>
 * @param <V>
 */
public class MemCache<A, V> implements Computable<A, V> {

	/**
	 * 缓存 队列
	 */
	private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();

	/**
	 * 通用计算接口
	 */
	private final Computable<A, V> comp1;

	public MemCache(Computable<A, V> comp1) {
		this.comp1 = comp1;
	}

	@Override
	public V compute(final A arg) throws InterruptedException {
		while (true) {
			Future<V> f = cache.get(arg);

			if (f == null) {
				Callable<V> eval = new Callable<V>() {

					@Override
					public V call() throws Exception {
						return comp1.compute(arg);
					}
				};

				FutureTask<V> ft = new FutureTask<V>(eval);

				f = cache.put(arg, ft);

				if (f == null) {
					f = ft;
					ft.run();
				}
			}

			try {
				return f.get();
			}
			//如果计算取消
			catch(CancellationException e1)
			{
				e1.printStackTrace();
				cache.remove(arg);
			}
			catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

	}

}
