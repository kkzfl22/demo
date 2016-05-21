package com.liujun.pattern.state.flow;

/**
 * 进行流程控制的上下文对象
 * @author liujun
 *
 */
public class Context 
{
	/**
	 * 初始化状态
	 */
	private FlowState init = new InitFlowState(this);
	
	/**
	 * 安全模式
	 */
	private FlowState security = new SecurityFlowState(this);
	
	
	/**
	 * 初始化完结
	 */
	private FlowState initOver = new InitOverFlowState(this);
	
	
	
	/**
	 * 当前状态
	 */
	private FlowState currFlowState;
	
	

	public Context()
	{
		this.currFlowState = init;
	}



	public FlowState getCurrFlowState()
	{
		return currFlowState;
	}



	public void setCurrFlowState(FlowState currFlowState)
	{
		this.currFlowState = currFlowState;
	}



	public FlowState getInit()
	{
		return init;
	}



	public FlowState getSecurity()
	{
		return security;
	}



	public FlowState getInitOver()
	{
		return initOver;
	}



	public void doService()
	{
		this.getCurrFlowState().service();
	}
	
	
	
	
}
