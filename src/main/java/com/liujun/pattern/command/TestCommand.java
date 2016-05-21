package com.liujun.pattern.command;

public class TestCommand {

	
	public static void main(String[] args) throws Exception {
		//士兵执行命令冲
		ExecuteCommandInf<String> soildier = new Soildier();
		
		//电脑传递命令
		TransmitCommandInf<String> compile = new CompileCompileImpl(soildier);
		
		//将军发布命令
		SendCommandInf<String> transmit = new General(compile);
		
		String value = transmit.sendCommand("向着罗曼底冲。。。");
		
		System.out.println(value);
		
	}
}
