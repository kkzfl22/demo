package com.liujun.pattern.state.common;

public class OpeningState extends LiftState
{

	@Override
	public void openDoor()
	{
		System.out.println("电梯正在运行...");
	}

	@Override
	public void closeDoor()
	{
		//设置为状态状态
		super.getContext().setCurrLiftState(super.getContext().getCloseDoorState());
		super.getContext().getCurrLiftState().closeDoor();
	}

	@Override
	public void run()
	{
		System.out.println("当前电梯门是开着，不能运行");
	}

	@Override
	public void stop()
	{
		System.out.println("当前电梯门 已经打开，不能停止");
	}

	@Override
	public void powerCut()
	{
		System.out.println("当前电梯门已经开了，突然断电了!");
		super.getContext().setCurrLiftState(super.getContext().getPowerCutState());
		super.getContext().getCurrLiftState().powerCut();
	}
}
