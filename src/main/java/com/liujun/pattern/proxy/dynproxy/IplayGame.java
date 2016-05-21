package com.liujun.pattern.proxy.dynproxy;

public interface IplayGame
{
	/**
	 * 登陆
	 * @param name
	 */
	public void login(String name);
	
	/**
	 * 杀boss接口
	 */
	public void killBoss();
}
