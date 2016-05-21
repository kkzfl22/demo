package com.liujun.pattern.builder.carbuild;


public class BmwCar extends CarModel
{


	@Override
	public void alarm()
	{
		System.out.println("宝马车的喇叭是嘀嘀响");
	}

	@Override
	protected void start()
	{
		System.out.println("插入钥匙，按下启动按钮打开电源");
	}

	@Override
	protected void stop()
	{
		System.out.println("踩下刹车，宝马车就停了");
	}

	@Override
	protected void engineBoom()
	{
		System.out.println("宝马车引擎声音是非常小的，哄哄响");
	}

}
