package com.liujun.pattern.backup.backupup;

public class Base
{
	/**
	 * 当前是父类中的数据
	 */
	private String name;
	
	

	public Base(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Base [name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
