package com.liujun.pattern.analysis.common;


/**
 * 抽象运算符号解析器
 * 
 * @author liujun
 * 
 */
public abstract class SymbolExpression extends Expression
{

	/**
	 * 算术符号左边
	 */
	protected Expression left;

	/**
	 * 算术符号右边
	 */
	protected Expression right;

	public SymbolExpression(Expression left, Expression right)
	{
		this.left = left;
		this.right = right;
	}

}
