package com.liujun.pattern.builder.carbuild;

import java.util.ArrayList;
import java.util.List;

/**
 * 制造宝马汽车
 * @author liujun
 *
 */
public class BmwCarBuilder implements CarBuilder
{

	
	@Override
	public CarModel createCarMode()
	{
		//设置制造顺序
		CarModel bmw = new BmwCar();
		
		//设置宝马车的顺序
		List<String> list = new ArrayList<String>();
		
		list.add("start");
		list.add("stop");
		list.add("engineBoom");
		list.add("alarm");
		
		bmw.setSeq(list);
		
		return bmw;
	}

}
