package com.liujun.pattern.factory.delayproductfactory;

/**
 * 白人的实现
 * @author liujun
 *
 */
public class WhiteHumanImpl implements HumanInf
{

	@Override
	public void getColor()
	{
		System.out.println("由于制造的时间太短，就成白人了。。。");
	}

	@Override
	public void talk()
	{
		System.out.println("跟白人交流说英语....");
	}

}
