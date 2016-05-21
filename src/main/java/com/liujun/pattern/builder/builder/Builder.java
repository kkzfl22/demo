package com.liujun.pattern.builder.builder;

/**
 * 定义工人接口，明确工人的职责
 * @author liujun
 *
 * @date 2015年1月30日
 * @vsersion 0.0.1
 */
public interface Builder {
	
	/**
	 * 工人建造窗户
	 */
	public void makeWindow();
	
	
	/**
	 * 工作建造地板
	 */
	public void makefoor();
	
	/**
	 * 最后向房主交房
	 * @return
	 */
	public Room getRoom();

}
