package com.liujun.pattern.analysis.common;

import java.util.Map;

/**
 * 变量解析器
 * 
 * @author liujun
 * 
 */
public class VarExpression extends Expression
{

	/**
	 * 键信息
	 */
	private String key;

	public VarExpression(String key)
	{
		this.key = key;
	}

	@Override
	public int interpreter(Map<String, Integer> var)
	{
		return var.get(this.key);
	}

}
