package com.liujun.pattern.analysis.common;

import java.util.Map;

/**
 * 解析器类
 * @author liujun
 *
 */
public abstract class Expression
{
	/**
	 * 进行解析公式中的参数与值,key为公式中的参数，value为公式中的值
	 * @param var
	 * @return
	 */
	public abstract int interpreter(Map<String,Integer> var);
}
