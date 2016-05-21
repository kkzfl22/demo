package com.liujun.pattern.state.common;

/**
 * 电梯正在运行状态
 * @author liujun
 *
 */
public class RunLiftState extends LiftState
{

	@Override
	public void openDoor()
	{
		System.out.println("当前电梯正在运行，不能开空，不然，吓死你");
	}

	@Override
	public void closeDoor()
	{
		System.out.println("当前电梯正在运行，门已经关了");
	}

	@Override
	public void run()
	{
		System.out.println("当前电梯正在运行中.....");
	}

	@Override
	public void stop()
	{
		System.out.println("当前电梯运行中，有人到达了楼层，需要停止...");
		super.getContext().setCurrLiftState(super.getContext().getStopState());
		super.getContext().getCurrLiftState().stop();
	}

	@Override
	public void powerCut()
	{
		System.out.println("当前电梯运行中，突然断电了...");
		super.getContext().setCurrLiftState(super.getContext().getPowerCutState());
		super.getContext().getCurrLiftState().powerCut();
	}

}
