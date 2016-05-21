package com.liujun.pattern.flyweight.tmp1;

public class TestMain
{
	public static void main(String[] args)
	{
		SignFactory factory = new SignFactory();
		
		//通过简单工厂拿对象
		factory.getSignInfo();
	}
}
