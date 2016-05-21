package com.liujun.pattern.factory.morefactory;

/**
 * 抽象工厂类，进行制造人类
 * @author liujun
 *
 */
public abstract class AbsMakeHumanFactory
{
	/**
	 * 进行制造人类
	 * @return 制造人类
	 */
	public abstract  HumanInf makeHuman();
}
