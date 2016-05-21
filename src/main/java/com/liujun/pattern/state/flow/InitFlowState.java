package com.liujun.pattern.state.flow;

public class InitFlowState implements FlowState
{
	
	/**
	 * 环境信息
	 */
	private Context context;
	
	public InitFlowState(Context context)
	{
		this.context = context;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void service()
	{
		System.out.println("当前执行初始化操作....");
		
		try
		{
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("当前初始化完毕，进行下一步");
		
		//设置下一步执行流程为
		this.context.setCurrFlowState(this.context.getSecurity());
		//执行下一步流程为方法
		this.context.getCurrFlowState().service();
	}

}
