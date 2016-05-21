package com.liujun.pattern.factory.abstractfactory.demo2;

/**
 * 进行制造人类的工厂接口
 * @author liujun
 *
 */
public interface HumanFactoryInf
{
	/**
	 * 制造黑人
	 * @return
	 */
	public HumanInf makeBlackHuman();
	
	
	/**
	 * 制造白人
	 * @return
	 */
	public HumanInf makeWhiteHuman();
	
	/**
	 * 制造黄人
	 * @return
	 */
	public HumanInf makeYellowHuman();
	
}
