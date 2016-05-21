package com.liujun.pattern.command;

/**
 * 兵用于执行命令
 * @author liujun
 *
 * @date 2015年5月6日
 * @vsersion 0.0.1
 */
public class Soildier implements ExecuteCommandInf<String> {

	@Override
	public String executeCommand(String param) throws Exception {
		
		System.out.println("当前士兵收到命令。。。。，开始执行");
		
		System.out.println("士兵执行命令:"+param);

		Thread.sleep(2000);
		
		System.out.println("士兵执行命令:over");
		
		return "已经冲诺曼底";
	}

}
