package com.liujun.mobile.swindle.console;

/**
 * 匹配规则符号信息
 * @author liujun
 * @date 2016年3月5日
 * @verion 0.0.1
 */
public enum RoleMatchSign
{
	
	/**
	 * 开达式开始符号
	 */
	MATCH_SIGN_START("{"),
	
	/**
	 * 开达式结束符号
	 */
	MATCH_SIGN_END("}"),
	
	
	/**
	 * 进行内容占位符设定开始
	 */
	MATCH_SIGN_CON_NUM_START("#("),
	
	
	/**
	 * 进行内容占位符设定结束
	 */
	MATCH_SIGN_CON_NUM_END(")"),
	
	
	/**
	 * 内容匹配占位
	 */
	MATCH_SIGN_CON_SQL(".{0,"),
	
	/**
	 * 开达式中用来分隔匹配关键字与非匹配关键字
	 */
	MATCH_SIGN_NOTORIN_MAT("&&"),
	
	/**
	 * 开达式中用来分隔第一个关键字信息
	 */
	MATCH_SIGN_ITEM_SPIT("\\|"),
	
	
	/**
	 * 排除分隔符
	 */
	MATCH_SIGN_EXCLUDE_SPIT(","),
	
	/**
	 * 匹配部分开始
	 */
	MATCH_SIGN_SQL_MATCH_START("("),
	
	
	/**
	 * 匹配部分结束
	 */
	MATCH_SIGN_SQL_MATCH_END(")"),
	
	;
	
	/**
	 * 符号信息
	 */
	private String sign;

	private RoleMatchSign(String sign)
	{
		this.sign = sign;
	}

	public String getSign()
	{
		return sign;
	}

	public void setSign(String sign)
	{
		this.sign = sign;
	}
	
	
	
	
}
