package com.liujun.pattern.factory.abstractfactory.demo2;

/**
 * 当前为制造女人的接口
 * @author liujun
 *
 */
public class FemaleHumanFactory implements HumanFactoryInf
{

	@Override
	public HumanInf makeBlackHuman()
	{
		return new FemaleBlackHuman();
	}

	@Override
	public HumanInf makeWhiteHuman()
	{
		return new FemaleWhiteHumna();
	}

	@Override
	public HumanInf makeYellowHuman()
	{
		return new FemaleYellowHuman();
	}

}
