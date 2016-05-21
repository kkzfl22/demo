package com.liujun.pattern.templatefunction;

public class MainCarRun
{
	public static void main(String[] args)
	{
		//1,得到一输宝马车行驶
		CarModel car = new BmwCarRun();
		
		car.startCar();
		
		//使用奔驰车行驶
		CarModel benz = new MercedesCarRun();
		benz.startCar();
	}
}
