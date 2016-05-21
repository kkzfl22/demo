package com.liujun.pattern.state.common;

public class PowerCutLiftState extends LiftState
{

	@Override
	public void openDoor()
	{
		System.out.println("当前断电了，不能开门，等电来");
	}

	@Override
	public void closeDoor()
	{
		System.out.println("当前断电了，不能关门，等电来");
	}

	@Override
	public void run()
	{
		System.out.println("当前断电了，不能运行，等电来");
	}

	@Override
	public void stop()
	{
		System.out.println("当前断电了，不能运行，等电来");
	}

	@Override
	public void powerCut()
	{
		System.out.println("当前电已经断了，等电来");
	}

}
