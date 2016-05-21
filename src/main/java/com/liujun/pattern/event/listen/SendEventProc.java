package com.liujun.pattern.event.listen;

/**
 * 事件发布对象
 * @author liujun
 *
 */
public class SendEventProc implements SendEventInf
{
	/**
	 * 事件处理者信息
	 */
	private ListenEventInf eventProc;

	/**
	 * 构造函数，需要知道事件的处理者信息
	 * @param eventProc
	 */
	public SendEventProc(ListenEventInf eventProc)
	{
		this.eventProc = eventProc;
	}

	@Override
	public void sendEvent()
	{
		System.out.println("当前发生了某事件,进行事件调用");
		
		Event even = new Event("“当前发生了事件，需要进行打印..”");
		
		eventProc.proc(even);
	}
	
	
	
}
