package com.liujun.pattern.factory.abstractfactory;

/**
 * 抽象工厂类，进行制造人类
 * @author liujun
 *
 */
public abstract class AbsMakeHumanFactory
{
	/**
	 * 进行制造人类
	 * @param name 类弄信息
	 * @return 制造人类
	 */
	public abstract <T extends HumanInf> T makeHuman(Class<T> className);
}
