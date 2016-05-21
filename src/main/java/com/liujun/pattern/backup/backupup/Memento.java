package com.liujun.pattern.backup.backupup;

import java.util.Map;

public class Memento
{
	/**
	 * 进行备忘录的数据进行备份
	 */
	private  Map<String, Object> map;

	public Memento(Map<String, Object> map)
	{
		this.map = map;
	}

	public Map<String, Object> getMap()
	{
		return map;
	}

	public void setMap(Map<String, Object> map)
	{
		this.map = map;
	}
	
	
}
