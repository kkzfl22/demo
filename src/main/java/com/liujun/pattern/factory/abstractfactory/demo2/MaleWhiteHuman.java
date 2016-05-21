package com.liujun.pattern.factory.abstractfactory.demo2;

public class MaleWhiteHuman extends AbstractWhiteHuman
{

	@Override
	public void getSex()
	{
		System.out.println("当前为白人男性");
	}

}
