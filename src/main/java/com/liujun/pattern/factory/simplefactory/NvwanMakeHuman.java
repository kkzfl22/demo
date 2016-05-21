package com.liujun.pattern.factory.simplefactory;

/**
 * 女祸制造人类
 * 使用简单工厂进行制
 * @author liujun
 *
 */
public class NvwanMakeHuman
{
	public static void main(String[] args)
	{
		//,开始制造黑人
		HumanInf blackMan = SimpleMakeHumanFacory.makeHuman(BlackHumanImpl.class);
		
		blackMan.getColor();
		blackMan.talk();
		
		//制造白人
		HumanInf whiteMan = SimpleMakeHumanFacory.makeHuman(WhiteHumanImpl.class);
		
		whiteMan.getColor();
		whiteMan.talk();
		
		//黄人
		HumanInf yellowMan = SimpleMakeHumanFacory.makeHuman(YellowHumanImpl.class);
		
		yellowMan.getColor();
		yellowMan.talk();
		
	}
}
