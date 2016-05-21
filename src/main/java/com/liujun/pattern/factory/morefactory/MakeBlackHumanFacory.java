package com.liujun.pattern.factory.morefactory;

/**
 * 制造人类
 * 
 * @author liujun
 * 
 */
public class MakeBlackHumanFacory extends AbsMakeHumanFactory
{

	@Override
	public HumanInf makeHuman()
	{
		return new BlackHumanImpl();
	}

}
