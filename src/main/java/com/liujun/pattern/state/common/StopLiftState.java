package com.liujun.pattern.state.common;

/**
 * 电梯停止状态
 * @author liujun
 *
 */
public class StopLiftState extends LiftState
{

	@Override
	public void openDoor()
	{
		System.out.println("电梯已经停止，可以开门");
		super.getContext().setCurrLiftState(super.getContext().getOpenDoorState());
		super.getContext().getCurrLiftState().openDoor();
	}

	@Override
	public void closeDoor()
	{
		System.out.println("当前电梯门已经处于关闭状态");
	}

	@Override
	public void run()
	{
		System.out.println("当前处于停止状态，可以运行");
		super.getContext().setCurrLiftState(super.getContext().getRunState());
		super.getContext().getCurrLiftState().run();
	}

	@Override
	public void stop()
	{
		System.out.println("当前电梯正在停止......");
	}

	@Override
	public void powerCut()
	{
		System.out.println("当前电梯停止，突然断电了!");
		super.getContext().setCurrLiftState(super.getContext().getPowerCutState());
		super.getContext().getCurrLiftState().powerCut();
	}

}
