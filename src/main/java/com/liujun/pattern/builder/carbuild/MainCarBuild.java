package com.liujun.pattern.builder.carbuild;

public class MainCarBuild
{
	public static void main(String[] args)
	{
		CarBuilder bmwbuild = new BmwCarBuilder();
		
		CarModel bmw= bmwbuild.createCarMode();
		
		bmw.run();
		
		System.out.println();
		//制造奔驰车
		CarBuilder benzBuild = new BenzCarBuilder();
		
		CarModel benz = benzBuild.createCarMode();
		
		benz.run();
	}
}
