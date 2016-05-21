package com.liujun.thread.threadlocal.util;

/**
 * 进行线程操作的工具类
 * @author liujun
 *
 */
public class ThreadUtils
{
	private static  ThreadLocal<String> LOCATON_DATA = new ThreadLocal<String>();
	
	public static void setData(String data)
	{
		LOCATON_DATA.set(data);
	}
	
	public static String getData()
	{
		return LOCATON_DATA.get();
	}
	
	
}
