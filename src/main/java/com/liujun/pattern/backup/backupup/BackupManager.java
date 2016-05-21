package com.liujun.pattern.backup.backupup;

import java.util.HashMap;
import java.util.Map;

public class BackupManager
{
	/**
	 * 当前备份对象
	 */
	private Map<String,Memento> mementoMap = new HashMap<String,Memento>();

	public Memento getMemento(String id)
	{
		if(mementoMap.containsKey(id))
		{
			return mementoMap.get(id);
		}
		
		return null;
	}

	public void addMemento(String id,Memento memento)
	{
		mementoMap.put(id, memento);
	}
	
	
}
