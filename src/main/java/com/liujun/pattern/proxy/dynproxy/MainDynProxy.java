package com.liujun.pattern.proxy.dynproxy;

import java.lang.reflect.InvocationHandler;

public class MainDynProxy
{
	public static void main(String[] args)
	{
		//1,声明对象
		IplayGame play = new PlayGameImp();
		
		//1,实例化接口实例
		InvocationHandler hander = new PlayGameHandle(play);
		
		//3,实例华动态代理对象
		IplayGame plays = DynProxyExec.newProxyInstance(play.getClass().getClassLoader(), play.getClass().getInterfaces(), hander);
		
		plays.login("刘军");
		
		plays.killBoss();
		
		
	}
}
