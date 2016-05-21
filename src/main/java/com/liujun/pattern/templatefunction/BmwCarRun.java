package com.liujun.pattern.templatefunction;

public class BmwCarRun extends CarModel
{

	@Override
	public void openTheDoor()
	{
		System.out.println("打开宝马车门!");
	}

	@Override
	public void insertTheKey()
	{
		System.out.println("放入钥匙至感应处");
	}

	@Override
	public void openEngine()
	{
		System.out.println("按下启动按钮，启动引擎");
	}

	@Override
	public void runAway()
	{
		System.out.println("宝马车在路上开始行驶");
	}

	@Override
	public void alarm()
	{
		System.out.println("宝马车喇叭响了嘀嘀嘀嘀");
	}

	@Override
	protected boolean isAlarm()
	{
		return true;
	}

}
