package com.liujun.mobile.swindle.bean;

import java.util.List;

public class MatchKey
{
	/**
	 * 主语
	 */
	private MainItem mainkey;
	
	/**
	 * 用来进行匹配的表达式,或者用来在谓语与宾语之间的占位符信息
	 */
	private String contExp;	
	
	
	/**
	 * 谓语结构
	 */
	private MainItem callKey;
	
	/**
	 * 主语与谓语的占位符信息
	 */
	private String callExp;
	
	/**
	 * 宾语
	 */
	private List<GuestItem> guestkey;

	public MainItem getMainkey()
	{
		return mainkey;
	}

	public void setMainkey(MainItem mainkey)
	{
		this.mainkey = mainkey;
	}

	public String getContExp()
	{
		return contExp;
	}

	public void setContExp(String contExp)
	{
		this.contExp = contExp;
	}

	public List<GuestItem> getGuestkey()
	{
		return guestkey;
	}

	public void setGuestkey(List<GuestItem> guestkey)
	{
		this.guestkey = guestkey;
	}

	public MainItem getCallKey()
	{
		return callKey;
	}

	public void setCallKey(MainItem callKey)
	{
		this.callKey = callKey;
	}

	public String getCallExp()
	{
		return callExp;
	}

	public void setCallExp(String callExp)
	{
		this.callExp = callExp;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("MatchKey [mainkey=");
		builder.append(mainkey);
		builder.append(", contExp=");
		builder.append(contExp);
		builder.append(", callKey=");
		builder.append(callKey);
		builder.append(", callExp=");
		builder.append(callExp);
		builder.append(", guestkey=");
		builder.append(guestkey);
		builder.append("]");
		return builder.toString();
	}

}
