package com.liujun.pattern.dynamicproxy.cglib;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 进行类似拦截器的操作
 * @author liujun
 *
 * @date 2015年5月4日
 * @vsersion 0.0.1
 */
public class TimeHandler implements MethodInterceptor {

	/**
	 * 日志对象
	 */
	private Logger log = Logger.getLogger(TimeHandler.class);

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		long start = System.currentTimeMillis();

		log.info(" TimeHandler start time :" + start);
		
		System.out.println("类似AOP 之前...");
		
		Object value = proxy.invokeSuper(obj, args);
		
		System.out.println("类似AOP 之后...");
		

		long end = System.currentTimeMillis();

		log.info(" TimeHandler end time :" + end);
		log.info(" TimeHandler time difference :" + (end - start) + "(s)");
		return value;
	}

}
