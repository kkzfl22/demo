package com.liujun.pattern.factory.delayproductfactory;

public class DelayProductFactoryMain
{

	public static void main(String[] args)
	{
		DelayProductFacory deply = new DelayProductFacory();

		HumanInf human = deply.getHumanObj("black");

		human.getColor();
		human.talk();

		HumanInf white = deply.getHumanObj("white");

		white.getColor();
		white.talk();

		HumanInf yellow = deply.getHumanObj("yellow");

		yellow.getColor();
		yellow.talk();
	}
}
