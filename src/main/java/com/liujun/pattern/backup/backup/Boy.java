package com.liujun.pattern.backup.backup;

public class Boy
{
	/**
	 * 状态信息
	 */
	private String state;

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}
	
	public void change()
	{
		this.state = "女孩子跑了，心情很糟";
	}
	
	public Backup recordState(String state)
	{
		return new Backup(this.state);
	}
	
	public void resetState(Backup back)
	{
		this.setState(back.getState());
	}
}
