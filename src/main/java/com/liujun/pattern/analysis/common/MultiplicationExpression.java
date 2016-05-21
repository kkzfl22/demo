package com.liujun.pattern.analysis.common;

import java.util.Map;

public class MultiplicationExpression extends SymbolExpression
{

	public MultiplicationExpression(Expression left, Expression right)
	{
		super(left, right);
	}

	@Override
	public int interpreter(Map<String, Integer> var)
	{
		return super.left.interpreter(var) * super.right.interpreter(var);
	}

}
