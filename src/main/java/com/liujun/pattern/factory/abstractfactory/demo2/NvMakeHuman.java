package com.liujun.pattern.factory.abstractfactory.demo2;


/**
 * 女娲造人
 * @author liujun
 *
 */
public class NvMakeHuman
{
	
	public static void main(String[] args)
	{
		//1,创建男人工厂
		HumanFactoryInf maleHumanFactory = new  MaleHumanFactory();
		
		HumanInf blackMan = maleHumanFactory.makeBlackHuman();
		printHman(blackMan);
		
		HumanInf whiteMan = maleHumanFactory.makeWhiteHuman();
		printHman(whiteMan);
		
		HumanInf yellowMan = maleHumanFactory.makeYellowHuman();
		printHman(yellowMan);
		
		//2,创建女人工厂
		HumanFactoryInf femaleHumanFactory = new FemaleHumanFactory();
		
		HumanInf femaleBlack =  femaleHumanFactory.makeBlackHuman();
		printHman(femaleBlack);

		HumanInf femaleWhite = femaleHumanFactory.makeWhiteHuman();
		printHman(femaleWhite);

		HumanInf femaleYellow = femaleHumanFactory.makeYellowHuman();
		printHman(femaleYellow);
	}
	
	public static void printHman(HumanInf human)
	{
		System.out.print("当前为\t");
		human.getColor();
		System.out.print("当前交谈\t");
		human.talk();
		System.out.print("当前的性别\t");
		human.getSex();
		System.out.println();
	}
	
}
