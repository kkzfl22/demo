package com.liujun.pattern.strategy;

public class GivenGreenService implements DoServiceInf
{

	@Override
	public void service(String name)
	{
		System.out.println("找吴国太开绿灯:"+name);
	}

}
