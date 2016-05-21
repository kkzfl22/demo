package com.liujun.mobile.swindle.bean;

import java.util.LinkedList;

/**
 * 匹配信息部分，主语匹配
 * @author liujun
 * @date 2016年2月27日
 * @verion 0.0.1
 */
public class MainItem
{
	
	/**
	 * 成功匹配的关键字
	 */
	private LinkedList<String> matchKey = new LinkedList<String>();	
	
	
	/**
	 * 不需要匹配的关键字，排除关键字信息
	 */
	private LinkedList<String> notMatchKey = new LinkedList<String>();


	public LinkedList<String> getMatchKey()
	{
		return matchKey;
	}


	public void addMatchKey(String matchKey)
	{
		this.matchKey.add(matchKey);
	}


	public LinkedList<String> getNotMatchKey()
	{
		return notMatchKey;
	}


	public void addNotMatchKey(String notMatchKey)
	{
		this.notMatchKey.add(notMatchKey);
	}
	


	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("RoleItem [matchKey=");
		builder.append(matchKey);
		builder.append(", notMatchKey=");
		builder.append(notMatchKey);
		builder.append("]");
		return builder.toString();
	}
	
}
