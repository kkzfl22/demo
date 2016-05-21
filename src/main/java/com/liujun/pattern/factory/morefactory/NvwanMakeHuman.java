package com.liujun.pattern.factory.morefactory;

/**
 * 多工厂制造
 * @author liujun
 *
 */
public class NvwanMakeHuman
{
	public static void main(String[] args)
	{
		//,开始制造黑人
		HumanInf blackMan = (new MakeBlackHumanFacory()).makeHuman();
		
		blackMan.getColor();
		blackMan.talk();
		
		//制造白人
		HumanInf whiteMan =  (new MakeWhiteHumanFacory()).makeHuman();
		
		whiteMan.getColor();
		whiteMan.talk();
		
		//黄人
		HumanInf yellowMan = (new MakeYellowHumanFacory()).makeHuman();
		
		yellowMan.getColor();
		yellowMan.talk();
		
	}
}
