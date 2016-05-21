package com.liujun.pattern.event;

/**
 * 事件观察者，当事件发生时，就进行通知
 * 如，老师来了，通知所有学生，不玩游戏了
 * @author liujun
 *
 * @date 2015年5月5日
 * @vsersion 0.0.1
 */
public class GoodNotifier extends Notifier {

	@Override
	public void addListen(EventType type,Object tarObj, String methodName, Object... args) {
		this.getEventHandler().addEvent(type,tarObj, methodName, args);
	}

	@Override
	public void notifly(EventType type) {
		System.out.println("放哨的同学报告，通知大家。。。。");
		try {
			this.getEventHandler().notiflyAll(type);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
