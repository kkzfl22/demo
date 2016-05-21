package com.liujun.pattern.builder.carbuild;

/**
 * 车辆建造接口
 * @author liujun
 *
 */
public interface CarBuilder
{
	/**
	 * 制造汽车
	 * @return
	 */
	public CarModel createCarMode();
}
