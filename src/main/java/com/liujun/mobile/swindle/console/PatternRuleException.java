package com.liujun.mobile.swindle.console;

/**
 * 规则匹配自定义异常
 * 
 * @author liujun
 * @date 2016年2月28日
 * @verion 0.0.1
 */
public class PatternRuleException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PatternRuleException()
	{
		super();
	}

	public PatternRuleException(String message)
	{
		super(message);
	}

}
