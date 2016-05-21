package com.liujun.pattern.factory.abstractfactory.demo2;

/**
 * 抽象黑人
 * @author liujun
 *
 */
public abstract class AbstractWhiteHuman implements HumanInf
{

	@Override
	public void getColor()
	{
		System.out.println("当前造人为白人");
	}

	@Override
	public void talk()
	{
		System.out.println("白人说英语");
	}

}
