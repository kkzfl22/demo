package com.liujun.pattern.event;

/**
 * 类型信息
 * @author liujun
 *
 * @date 2015年5月5日
 * @vsersion 0.0.1
 */
public enum EventType {
	
	/**
	 * 类型1，代表老师来了
	 */
	TEACHER_COME(1),
	
	/**
	 * 老师走
	 */
	TEACHER_GO(2),
	
	;
	
	private int type;

	private EventType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	

}
