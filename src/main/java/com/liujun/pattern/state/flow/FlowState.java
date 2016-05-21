package com.liujun.pattern.state.flow;

/**
 * 基本的流程对象
 * @author liujun
 *
 */
public interface FlowState
{
	/**
	 * 进行流程操作的service方法
	 */
	public void service();
}
