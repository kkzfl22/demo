package com.liujun.pattern.state.common;

/**
 * 电梯正在关门
 * @author liujun
 *
 */
public class CloseLiftState extends LiftState
{

	@Override
	public void openDoor()
	{
		System.out.println("当前电梯正在关门中。。。，突然按了开门，有人要上电梯!");	
		super.getContext().setCurrLiftState(super.getContext().getOpenDoorState());
		super.getContext().getCurrLiftState().openDoor();
	}

	@Override
	public void closeDoor()
	{
		System.out.println("电梯正在关门...");
	}

	@Override
	public void run()
	{
		super.getContext().setCurrLiftState(super.getContext().getRunState());
		super.getContext().getCurrLiftState().run();
	}

	@Override
	public void stop()
	{
		System.out.println("电梯正在关门，不能停止");
	}

	@Override
	public void powerCut()
	{
		System.out.println("电梯正在关门中，突然断电了...");
		super.getContext().setCurrLiftState(super.getContext().getPowerCutState());
		super.getContext().getCurrLiftState().powerCut();
	}

}
