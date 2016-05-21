package com.liujun.pattern.builder.carbuild;

import java.util.ArrayList;
import java.util.List;

/**
 * 制造宝马汽车
 * @author liujun
 *
 */
public class BenzCarBuilder implements CarBuilder
{

	
	@Override
	public CarModel createCarMode()
	{
		//设置制造顺序
		CarModel benz = new BenzCar();
		
		//设置奔驰车的顺序
		List<String> list = new ArrayList<String>();
		
		list.add("start");
		list.add("stop");
		list.add("alarm");
		list.add("engineBoom");
		
		benz.setSeq(list);
		
		return benz;
	}

}
