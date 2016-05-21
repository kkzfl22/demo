package com.liujun.pattern.access;

public interface PrintInfo extends Ivisitor
{
	/**
	 * 打印信息
	 */
	public void print();
	
	
	/**
	 * 进行计算
	 * @return
	 */
	public int count();
}
