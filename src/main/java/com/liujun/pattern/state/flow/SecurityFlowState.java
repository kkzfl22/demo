package com.liujun.pattern.state.flow;

public class SecurityFlowState implements FlowState
{
	/**
	 * 上下文对象
	 */
	private Context context;

	public SecurityFlowState(Context context)
	{
		this.context = context;
	}

	@SuppressWarnings("static-access")
	@Override
	public void service()
	{
		System.out.println("当前执行安全模式方法....");

		try
		{
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("安全模式结束...");

		// 标识流程执行结束
		this.context.setCurrFlowState(this.context.getInitOver());
		// 进行结束的数据清理
		this.context.getCurrFlowState().service();
	}

}
