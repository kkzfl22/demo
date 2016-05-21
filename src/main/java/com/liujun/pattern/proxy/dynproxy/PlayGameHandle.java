package com.liujun.pattern.proxy.dynproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 使用动态代码接口
 * 
 * @author liujun
 * 
 */
public class PlayGameHandle implements InvocationHandler
{
	/**
	 * 目标对象
	 */
	private Object target;

	public PlayGameHandle(Object target)
	{
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		// 使用之前运行
		BeforeExec before = new PlayGameLoginBeforeExec();

		before.beforeCall();
		Object rsp = method.invoke(target, args);

		// 之后进行运行
		EndExec end = new PlayGameEndExec();
		end.endExec();

		return rsp;
	}

}
