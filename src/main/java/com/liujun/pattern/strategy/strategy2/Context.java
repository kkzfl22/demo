package com.liujun.pattern.strategy.strategy2;

public class Context
{
	private Calculator _calculator;

	public Context(Calculator _calculator)
	{
		this._calculator = _calculator;
	}
	
	public int exec(int a,int b)
	{
		return this._calculator.exec(a, b);
	}
	
}
