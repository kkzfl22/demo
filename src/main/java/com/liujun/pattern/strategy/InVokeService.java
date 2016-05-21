package com.liujun.pattern.strategy;

public class InVokeService
{
	/**
	 * 执行方法
	 */
	private DoServiceInf doService = new DoBackDoorService();
	
	
	private DoServiceInf givenGreen = new GivenGreenService();
	
	
	private DoServiceInf woman = new WomanService();

	
	public void invoke(String type)
	{
		if("1".equals(type))
		{
			doService.service("当前为1");
		}
		else if("2".equals(type))
		{
			givenGreen.service("当前为2");
		}
		else if("3".equals(type))
		{
			woman.service("当前为3");
		}
	}
}
