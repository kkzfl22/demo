package com.liujun.pattern.analysis.common;

import java.util.Map;
import java.util.Stack;

public class Calculator
{
	/**
	 * 定义表达式
	 */
	private Expression expression;

	/**
	 * 构造函数传递表达式
	 * 
	 * @param expStr
	 */
	public Calculator(String expStr)
	{
		// 定义一个容器对象，栈，先进后出
		Stack<Expression> expStack = new Stack<Expression>();

		// 表达式拆分
		char[] expStrArr = expStr.toCharArray();

		Expression left = null;
		Expression right = null;

		for (int i = 0; i < expStrArr.length; i++)
		{
			switch (expStrArr[i])
			{
			case '+':
				// 从栈的顶部拿出对象
				left = expStack.pop();
				right = new VarExpression(String.valueOf(expStrArr[++i]));
				expStack.push(new AddExpression(left, right));
				break;
			case '-':
				// 从栈的顶部拿出对象
				left = expStack.pop();
				right = new VarExpression(String.valueOf(expStrArr[++i]));
				expStack.push(new SubExpression(left, right));
				break;
			case '*':
				// 从栈的顶部拿出对象
				left = expStack.pop();
				right = new VarExpression(String.valueOf(expStrArr[++i]));
				expStack.push(new MultiplicationExpression(left, right));
				break;
			case '/':
				// 从栈的顶部拿出对象
				left = expStack.pop();
				right = new VarExpression(String.valueOf(expStrArr[++i]));
				expStack.push(new DivisionExpression(left, right));
				break;
			// 如果当前不为操作符，则需要将数据放入至栈中
			default:
				expStack.push(new VarExpression(String.valueOf(expStrArr[i])));
			}
		}

		this.expression = expStack.pop();
	}

	public int runExec(Map<String, Integer> var)
	{
		return this.expression.interpreter(var);
	}

}
