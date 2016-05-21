package com.liujun.pattern.dynamicproxy.jdk;

import java.lang.reflect.Method;

/**
 * 动态代理的接口
 * @author liujun
 *
 * @date 2015年5月4日
 * @vsersion 0.0.1
 */
public interface InvocationHandler {
	
	
	/**
	 * 代理类的核心接口
	 * @param o 代理的对象
	 * @param m 方法信息
	 */
	public void invoke(Object o, Method m);
}
