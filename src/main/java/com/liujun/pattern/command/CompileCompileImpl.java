package com.liujun.pattern.command;

/**
 * 计算机担任发布命令的角色
 * 
 * @author liujun
 * 
 * @date 2015年5月6日
 * @vsersion 0.0.1
 */
public class CompileCompileImpl implements TransmitCommandInf<String> {

	/**
	 * 用来进行命令执行的对象
	 */
	private final ExecuteCommandInf<String> exec;

	/**
	 * 构造函数需要指出其命令执行的对象
	 * 
	 * @param exec
	 */
	public CompileCompileImpl(ExecuteCommandInf<String> exec) {
		this.exec = exec;
	}

	@Override
	public String transmitCommand(String param) throws Exception {
		System.out.println("当前我是命令传递对象，进行传递命令:" + param);

		String rsp = String.valueOf(exec.executeCommand(param));

		System.out.println("当前我是命令命令对象,传递结束:" + rsp);

		return rsp;
	}

}
