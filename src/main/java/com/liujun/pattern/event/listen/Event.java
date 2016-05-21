package com.liujun.pattern.event.listen;

public class Event
{
	/**
	 * 发生的事件信息
	 */
	private Object obj;

	
	
	
	public Event(Object obj)
	{
		this.obj = obj;
	}


	/**
	 * 获得事件对象信息
	 * @return
	 */
	public Object getObj()
	{
		return obj;
	}
	
}
