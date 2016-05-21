package com.liujun.pattern.backup.tmp1;

public class Boy
{
	/**
	 * 当前的状态
	 */
	private String state;
	
	public void changState()
	{
		this.state = "当前追求失败，心情很糟!";
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}
	
	
	
}
