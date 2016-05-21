package com.liujun.pattern.factory.singlefactory;

public class MainSingleton
{
	public static void main(String[] args)
	{
		//通过单例工厂创建单例对象
		SingletonObj singleton = SingletonFactory.getSingleton();
		
		singleton.doSameSingle();
	}
}
