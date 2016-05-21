package com.liujun.pattern.factory.abstractfactory.demo2;

/**
 * 抽象黑人
 * @author liujun
 *
 */
public abstract class AbstractBlackHuman implements HumanInf
{

	@Override
	public void getColor()
	{
		System.out.println("当前造人为黑人");
	}

	@Override
	public void talk()
	{
		System.out.println("黑人说印弟安语");
	}

}
