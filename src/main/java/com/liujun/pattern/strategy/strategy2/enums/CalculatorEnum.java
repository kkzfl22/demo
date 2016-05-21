package com.liujun.pattern.strategy.strategy2.enums;

public enum CalculatorEnum
{

	ADD
	{
		public int exec(int a, int b)
		{
			return a + b;
		}
	},

	SUB
	{
		public int exec(int a, int b)
		{
			return a - b;
		}
	};

	/**
	 * 进行运算
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public abstract int exec(int a, int b);
}
