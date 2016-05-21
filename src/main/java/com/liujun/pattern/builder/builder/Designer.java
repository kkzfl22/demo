package com.liujun.pattern.builder.builder;

/**
 * 设计师的工作是指挥民工怎么靠房子
 * @author liujun
 *
 * @date 2015年1月30日
 * @vsersion 0.0.1
 */
public class Designer {
	
	public void order(Builder builder)
	{
		//选择高窗户，再搞地板
		builder.makeWindow();
		builder.makefoor();
	}

}
