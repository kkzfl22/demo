package com.liujun.pattern.factory.singlefactory;

public class SingletonObj
{
	private SingletonObj()
	{
		
	}
	
	public void doSameSingle()
	{
		System.out.println("得到当前的单例对象");		
	}
}
