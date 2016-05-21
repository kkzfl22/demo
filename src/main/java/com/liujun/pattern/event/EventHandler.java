package com.liujun.pattern.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * EventHandler类，若干Event类的载体，同时提供一个执行所有Event的方法
 * 
 * @author liujun
 * 
 * @date 2015年5月5日
 * @vsersion 0.0.1
 */
public class EventHandler {

	/**
	 * 事件集体对象
	 */
	private Map<EventType, List<Event>> eventMaps;

	public EventHandler() {
		eventMaps = new ConcurrentHashMap<EventType, List<Event>>();
	}

	/**
	 * 添加事件通知
	 * 
	 * @param tarObj
	 *            通知源对象
	 * @param methodName
	 *            通知的方法
	 * @param metParam
	 *            参数
	 */
	public void addEvent(EventType type, Object tarObj, String methodName, Object... metParam) {
		Event event = new Event(tarObj, methodName, metParam);

		List<Event> list = this.eventMaps.get(type);

		if (null != list) {
			list.add(event);
		} else {
			list = new ArrayList<Event>();
			list.add(event);
		}

		eventMaps.put(type, list);
	}

	/**
	 * 进行通知
	 * 
	 * @throws Exception
	 */
	public void notiflyAll(EventType type) throws Exception {
		if (null != type) {
			//取出事件集体对象
			List<Event> enArrays = eventMaps.get(type);
			
			//进行通知s
			if (null != enArrays && !enArrays.isEmpty()) {
				for (Event event : enArrays) {
					event.invoke();
				}
			}
		}
	}
}
