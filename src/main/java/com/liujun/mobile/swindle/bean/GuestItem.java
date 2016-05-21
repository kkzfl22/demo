package com.liujun.mobile.swindle.bean;

import java.util.LinkedList;

/**
 * 宾语部分
 * 
 * @author liujun
 * @date 2016年3月2日
 * @verion 0.0.1
 */
public class GuestItem
{
	/**
	 * 宾语的词信息
	 */
	private String key;

	/**
	 * 排除关键字
	 */
	private LinkedList<String> exclude;

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public LinkedList<String> getExclude()
	{
		return exclude;
	}

	public void setExclude(LinkedList<String> excludeItem)
	{
		this.exclude = excludeItem;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("GuestItem [key=");
		builder.append(key);
		builder.append(", exclude=");
		builder.append(exclude);
		builder.append("]");
		return builder.toString();
	}

}
