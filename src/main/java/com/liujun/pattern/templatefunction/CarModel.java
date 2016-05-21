package com.liujun.pattern.templatefunction;

public abstract class CarModel
{
	/**
	 * 打开车门
	 */
	public abstract void openTheDoor();

	/**
	 * 插入钥匙
	 */
	public abstract void insertTheKey();

	/**
	 * 启动汽车
	 */
	public abstract void openEngine();

	/**
	 * 汽车开始行驶
	 */
	public abstract void runAway();

	/**
	 * 按喇叭
	 */
	public abstract void alarm();

	/**
	 * 开车行驶
	 */
	public final void startCar()
	{
		// 1,找开车门
		openTheDoor();
		// 2,插入钥匙
		insertTheKey();
		// 3,启动引擎
		openEngine();
		// 4,开始行驶
		runAway();

		if (isAlarm())
		{
			// 5,路上遇到一条狗，旺旺
			alarm();
		}
	}

	protected boolean isAlarm()
	{
		return false;
	}

}
