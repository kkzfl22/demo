package com.liujun.pattern.command;

/**
 * 
 * 用来进行命令的发布操作
 * @author liujun
 *
 * @date 2015年5月6日
 * @vsersion 0.0.1
 */
public interface SendCommandInf<T> {
	
	/**
	 * 用来进行命令发布
	 */
	public T sendCommand(T param)throws Exception;
}
