package com.liujun.pattern.state.flow;

public class InitOverFlowState implements FlowState
{

	/**
	 * 上下文对象
	 */
	private Context conext;

	public InitOverFlowState(Context conext)
	{
		this.conext = conext;
	}

	@SuppressWarnings("static-access")
	@Override
	public void service()
	{
		System.out.println("初始化完毕，进行清理，开始");

		try
		{
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		System.out.println(conext.getCurrFlowState());

		System.out.println("初始化完毕，进行清理，结束...");

	}

}
