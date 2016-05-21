package com.liujun.pattern.backup.backup;

public class Backup
{
	/**
	 * 状态信息
	 */
	private String state;
	

	public Backup(String state)
	{
		this.state = state;
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
