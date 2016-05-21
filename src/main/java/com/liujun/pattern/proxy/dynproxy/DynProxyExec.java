package com.liujun.pattern.proxy.dynproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynProxyExec
{
	public static <T> T newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
	{
		T obj = null;
		
		
		obj = (T) Proxy.newProxyInstance(loader, interfaces, h);
		
		
		return obj;
	}
}
