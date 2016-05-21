package com.liujun.pattern.dynamicproxy.jdk;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

/**
 * 进行时间的动态代理操作计算时间
 * @author liujun
 *
 * @date 2015年5月4日
 * @vsersion 0.0.1
 */
public class TimeHandler implements InvocationHandler {
	
	/**
	 * 日志对象
	 */
	private Logger log = Logger.getLogger(TimeHandler.class);

	/**
	 * 代理的对象
	 */
	private Object target;

	public TimeHandler(Object target) {
		super();
		this.target = target;
	}

	@Override
	public void invoke(Object o, Method m) {
		long start = System.currentTimeMillis();
		
		log.info(" TimeHandler start time :"+start);
		
		try {
			m.invoke(target);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		
		log.info(" TimeHandler end time :"+end);
		log.info(" TimeHandler time difference :"+(end-start) + "(s)");
	}

}
