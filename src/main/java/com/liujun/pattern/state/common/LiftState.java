package com.liujun.pattern.state.common;

public abstract class LiftState
{
	/**
	 * 进行操作的上文对象
	 */
	private Context context;

	public Context getContext()
	{
		return context;
	}

	public void setContext(Context context)
	{
		this.context = context;
	}

	/**
	 * 开门
	 */
	public abstract void openDoor();

	/**
	 * 关门
	 */
	public abstract void closeDoor();

	/**
	 * 电梯运行
	 */
	public abstract void run();

	/**
	 * 电梯停止
	 */
	public abstract void stop();

	/**
	 * 停电操作
	 */
	public abstract void powerCut();
	
}
