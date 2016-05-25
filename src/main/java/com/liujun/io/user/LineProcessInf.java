package com.liujun.io.user;

/**
 * 行数据处理接口
 * @author liujun
 *
 * @date 2016年5月24日
 * @vsersion 0.0.1
 */
public interface LineProcessInf {
	
	/**
	 * 进行行数据处理
	 * @param line
	 * @return
	 * @throws Exception
	 */
	public String processLine(String line)throws Exception;
	
}
