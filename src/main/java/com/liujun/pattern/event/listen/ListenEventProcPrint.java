package com.liujun.pattern.event.listen;

/**
 * 监听事件信息
 * @author liujun
 *
 */
public class ListenEventProcPrint implements ListenEventInf
{

	@Override
	public void proc(Event event)
	{
		System.out.println("当前的事件处理为打印，将打印出监听的信息,信息为:"+event.getObj());
	}
	
}
