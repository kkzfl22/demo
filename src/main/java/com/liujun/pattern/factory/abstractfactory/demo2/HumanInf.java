package com.liujun.pattern.factory.abstractfactory.demo2;

/**
 * 人类
 * @author liujun
 *
 */
public interface HumanInf
{
	/**
	 * 产生人的类的颜色
	 */
	public void getColor();
	
	/**
	 * 交谈
	 */
	public void talk();
	
	/**
	 * 得到性别
	 */
	public void getSex();
}
