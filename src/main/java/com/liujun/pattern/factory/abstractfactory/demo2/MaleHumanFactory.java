package com.liujun.pattern.factory.abstractfactory.demo2;

/**
 * 当前为制造男性的工厂接口信息
 * @author liujun
 *
 */
public class MaleHumanFactory implements HumanFactoryInf
{

	@Override
	public HumanInf makeBlackHuman()
	{
		return new MaleBlackHuman();
	}

	@Override
	public HumanInf makeWhiteHuman()
	{
		return new MaleWhiteHuman();
	}

	@Override
	public HumanInf makeYellowHuman()
	{
		return new MaleYellowHuman();
	}

}
