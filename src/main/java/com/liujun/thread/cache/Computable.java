package com.liujun.thread.cache;

/**
 * 
 * 进行通用计算接口
 * @author Administrator
 *
 * @param <A>
 * @param <V>
 */
public interface Computable<A,V> {
	
	/**
	 * 进行通用计算方法
	 * @param arg 参数信息
	 * @return 结果信息
	 * @throws InterruptedException
	 */
	public V compute(A arg) throws InterruptedException;

}
