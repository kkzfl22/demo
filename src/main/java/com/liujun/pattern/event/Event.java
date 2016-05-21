package com.liujun.pattern.event;

import java.lang.reflect.Method;

/**
 * 定义一个事件类,用于事件传递的对象信息
 * 
 * @author liujun
 * 
 * @date 2015年5月5日
 * @vsersion 0.0.1
 */
public class Event {

	/**
	 * 需要执行方法的对象
	 */
	private Object tarObj;

	/**
	 * 需要执行的方法的名称
	 */
	private String methodName;

	/**
	 * 方法的参数
	 */
	private Object[] metParam;

	/**
	 * 要执行方法的参数类型
	 */
	@SuppressWarnings("rawtypes")
	private Class[] paramTypes;

	public Event() {

	}

	public Event(Object tarObj, String methodName, Object... metParam) {
		this.tarObj = tarObj;
		this.methodName = methodName;
		this.metParam = metParam;
		contractParamTypes(this.metParam);
	}

	/**
	 * 根据参数数组生成参数类型数组
	 * @param params 类型数组信息
	 */
	private void contractParamTypes(Object[] params) {
		this.paramTypes = new Class[params.length];
		for (int i = 0; i < params.length; i++) {
			this.paramTypes[i] = params[i].getClass();
		}
	}

	public Object getTarObj() {
		return tarObj;
	}

	public void setTarObj(Object tarObj) {
		this.tarObj = tarObj;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getMetParam() {
		return metParam;
	}

	public void setMetParam(Object[] metParam) {
		this.metParam = metParam;
	}

	@SuppressWarnings("rawtypes")
	public Class[] getParamTypes() {
		return paramTypes;
	}

	@SuppressWarnings("rawtypes")
	public void setParamTypes(Class[] paramTypes) {
		this.paramTypes = paramTypes;
	}
	
	/**
	 * 执行事件所对应的方法
	 * @throws Exception
	 */
	public void invoke() throws Exception
	{
		Method meth = tarObj.getClass().getMethod(this.getMethodName(), this.getParamTypes());
		
		if(null == meth)
		{
			return;
		}
		meth.invoke(this.getTarObj(),this.getMetParam());
	}

}
