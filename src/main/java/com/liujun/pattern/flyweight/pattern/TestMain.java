package com.liujun.pattern.flyweight.pattern;

public class TestMain
{
	public static void main(String[] args)
	{
		SignFactory factory = new SignFactory();
		
		for (int i = 0; i < 20; i++)
		{
			factory.getSignInfo("科目1考试地点"+i);
		}
		
		SignInfo sign = factory.getSignInfo("科目1考试地点2");
		
		System.out.println(sign);
	}
}
