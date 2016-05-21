package com.liujun.pattern.templatefunction;

public class MercedesCarRun extends CarModel
{

	@Override
	public void openTheDoor()
	{
		System.out.println("打开奔驰车车门");
	}

	@Override
	public void insertTheKey()
	{
		System.out.println("插入奔驰车钥匙");
	}

	@Override
	public void openEngine()
	{
		System.out.println("启动奔驰车引擎");
	}

	@Override
	public void runAway()
	{
		System.out.println("奔驰车开始上路");
	}

	@Override
	public void alarm()
	{
		System.out.println("奔驰车喇叭也响了嘀嘀");
	}

}
