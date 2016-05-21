package com.liujun.pattern.factory.abstractfactory;

/**
 * 女祸制造人类
 * @author liujun
 *
 */
public class NvwanMakeHuman
{
	public static void main(String[] args)
	{
		//制造八卦炉
		AbsMakeHumanFactory make = new MakeHumanFacory();
		//,开始制造黑人
		HumanInf blackMan = make.makeHuman(BlackHumanImpl.class);
		
		blackMan.getColor();
		blackMan.talk();
		
		//制造白人
		HumanInf whiteMan = make.makeHuman(WhiteHumanImpl.class);
		
		whiteMan.getColor();
		whiteMan.talk();
		
		//黄人
		HumanInf yellowMan = make.makeHuman(YellowHumanImpl.class);
		
		yellowMan.getColor();
		yellowMan.talk();
		
	}
}
