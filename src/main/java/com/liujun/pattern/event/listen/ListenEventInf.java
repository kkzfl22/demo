package com.liujun.pattern.event.listen;

/**
 * 监听事件处理接口
 * @author liujun
 *
 */
public interface ListenEventInf
{
	/**
	 * 进行事件处理接口
	 * @param event 事件对象
	 */
	public void proc(Event event);
}
