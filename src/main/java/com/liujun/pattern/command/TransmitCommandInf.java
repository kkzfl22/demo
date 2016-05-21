package com.liujun.pattern.command;

/**
 * 命令接口，用于传递命令信息
 * @author liujun
 *
 * @date 2015年5月6日
 * @vsersion 0.0.1
 */
public interface TransmitCommandInf<T> {
	
	
	/**
	 * 用于传递命令
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public T transmitCommand(T param)throws Exception;

}
