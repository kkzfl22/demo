package com.liujun.pattern.factory.simplefactory;

/**
 * 黄种人实现
 * @author liujun
 *
 */
public class YellowHumanImpl implements HumanInf
{

	@Override
	public void getColor()
	{
		System.out.println("由于烧制刚刚好,当前是黄种人...");
	}

	@Override
	public void talk()
	{
		System.out.println("跟黄种人交谈说汉话");
	}

}
