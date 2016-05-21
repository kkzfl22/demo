package com.liujun.pattern.strategy.strategy2;

public class TestMain
{
	public static void main(String[] args)
	{
		String operation = "-";
		int a = 10, b =20;
		
		Context _conx = null;
		
		if("+".equals(operation))
		{
			_conx = new Context(new Add());
		}
		else if("-".equals(operation))
		{
			_conx = new Context(new Sub());
		}
		
		int value = _conx.exec(a, b);
		
		System.out.println(value);
	}
}
