package com.liujun.pattern.event;

/**
 * 进行一个抽象的通知方法
 * @author liujun
 *
 * @date 2015年5月5日
 * @vsersion 0.0.1
 */
public abstract class Notifier {
	
	/**
	 * 默认事件通知
	 */
	private EventHandler eventHandler = new EventHandler();

	public EventHandler getEventHandler() {
		return eventHandler;
	}

	public void setEventHandler(EventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}

	
	
	/**
	 * 添加需要监听的对象信息
	 * @param tarObj 目标对象
	 * @param methodName 目标方法名称
	 * @param args 参数信息
	 */
	public abstract void addListen(EventType type,Object tarObj,String methodName,Object...args);


	/**
	 * 通知方法
	 * @param type 事件类型
	 */
	public abstract void notifly(EventType type);
}
