package com.liujun.pattern.command;

/**
 * 将定进行发布命令
 * @author liujun
 *
 * @date 2015年5月6日
 * @vsersion 0.0.1
 */
public class General implements SendCommandInf<String> {
	
	/**
	 * 进行命令的发布
	 */
	private final TransmitCommandInf<String> transmitCommand;
	

	public General(TransmitCommandInf<String> transmitCommand) {
		this.transmitCommand = transmitCommand;
	}


	@Override
	public String sendCommand(String param) throws Exception {
		System.out.println("通过将军发布命令....:"+param);
		String rsp = String.valueOf(transmitCommand.transmitCommand(param));
		System.out.println("通过将军结果:"+rsp);
		return rsp;
	}






}
