package com.liujun.pattern.factory.delayproductfactory;

/**
 * 黑人的实现
 * @author liujun
 *
 */
public class BlackHumanImpl implements HumanInf
{

	@Override
	public void getColor()
	{
		System.out.println("当前由于烧的时间太长，就成黑人了");
	}

	@Override
	public void talk()
	{
		System.out.println("跟黑人说话，不知道说什么语");
	}

}
