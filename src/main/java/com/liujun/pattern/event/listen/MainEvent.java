package com.liujun.pattern.event.listen;

public class MainEvent
{
	public static void main(String[] args)
	{
		//1,创建事件处理对象
		ListenEventInf listen = new ListenEventProcPrint();
		
		//2,创建事件源对象
		SendEventInf sendEvent = new SendEventProc(listen);
		
		//当前发生了某事，进行通知
		sendEvent.sendEvent();
	}
}
