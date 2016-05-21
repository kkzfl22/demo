package com.liujun.pattern.strategy;

public class WomanService implements DoServiceInf
{

	@Override
	public void service(String name)
	{
		System.out.println("找孙夫人断后:"+name);
	}

}
