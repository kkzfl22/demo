package com.liujun.pattern.factory.singlefactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 得到单例对象工厂
 * 
 * @author liujun
 * 
 */
public class SingletonFactory
{
	/**
	 * 单例对象
	 */
	private static SingletonObj singleObj;

	static
	{

		try
		{
			@SuppressWarnings("unchecked")
			Class cl = Class.forName(SingletonObj.class.getName());

			// 获得构造函数
			@SuppressWarnings("unchecked")
			Constructor constructor = cl.getDeclaredConstructor();

			// 设置无参构造函数是可以访问的
			constructor.setAccessible(true);

			// 通过构造函数产生一个实例对象
			singleObj = (SingletonObj) constructor.newInstance();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (NoSuchMethodException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SingletonObj getSingleton()
	{
		return singleObj;
	}
}
