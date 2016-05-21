package com.liujun.pattern.mediator;

/**
 * 用于进行操作的接口信息
 * @author liujun
 *
 * @date 2015年5月6日
 * @vsersion 0.0.1
 */
public interface OperatorInf {
	
	/**
	 * 自己需要完成的工作
	 * @param mediator 中介者对象信息
	 */
	public void mySelfWork(AbstractMediator mediator);
	
	
	/**
	 * 当自己完成工作以后，通知谁来完成的工作
	 * @param mediator  中介者对象信息
	 */
	public void nofiflyOther(AbstractMediator mediator)throws Exception;

}
