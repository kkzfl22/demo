package com.liujun.pattern.builder.carbuild;

public class BenzCar extends CarModel
{

	@Override
	protected void start()
	{
		System.out.println("插入钥匙，打开奔驰车的电源");
	}

	@Override
	protected void stop()
	{
		System.out.println("踩下刹车，奔驰车就停了");
	}

	@Override
	protected void alarm()
	{
		System.out.println("奔驰车的喇叭响是嘀嘀响");
	}

	@Override
	protected void engineBoom()
	{
		System.out.println("奔驰车的引擎声音是非常小的，隆隆响的");
	}

}
