package com.liujun.pattern.strategy.strategy2;

public class Sub implements Calculator
{

	@Override
	public int exec(int a, int b)
	{
		return a-b;
	}

}
