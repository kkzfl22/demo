package com.liujun.pattern.strategy;

public class DoBackDoorService implements DoServiceInf
{

	@Override
	public void service(String name)
	{
		System.out.println("当前是找乔国老开后门的方法:"+name);
	}

}
