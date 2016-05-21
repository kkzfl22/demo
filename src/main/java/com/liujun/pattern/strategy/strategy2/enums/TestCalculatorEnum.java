package com.liujun.pattern.strategy.strategy2.enums;


public class TestCalculatorEnum
{
	public static void main(String[] args)
	{
		int a = 10;
		int b = 30;
		
		int value = CalculatorEnum.ADD.exec(a, b);
		
		System.out.println(value);
	}
}
