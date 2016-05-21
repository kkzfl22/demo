package com.liujun.pattern.command;

/**
 * 用于执行信息的接口信息
 * @author liujun
 *
 * @date 2015年5月6日
 * @vsersion 0.0.1
 */
public interface ExecuteCommandInf<T> {
	
	/**
	 * 进行进行命令执行接口
	 * @param param 参数信息
	 * @return 执行结果
	 * @throws Exception
	 */
	public Object executeCommand(T param)throws Exception;

}
