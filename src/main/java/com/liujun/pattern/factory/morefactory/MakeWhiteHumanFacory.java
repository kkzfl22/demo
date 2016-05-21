package com.liujun.pattern.factory.morefactory;

/**
 * 制造人类
 * 
 * @author liujun
 * 
 */
public class MakeWhiteHumanFacory extends AbsMakeHumanFactory
{

	@Override
	public HumanInf makeHuman()
	{
		return new WhiteHumanImpl();
	}

}
