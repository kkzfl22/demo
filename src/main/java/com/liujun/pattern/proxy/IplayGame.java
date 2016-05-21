package com.liujun.pattern.proxy;

/**
 * 进行打游戏接口
 * @author liujun
 *
 */
public interface IplayGame
{
	
	/**
	 * 登陆游戏帐号
	 * @param name 用户名
	 * @param passwd 密码
	 */
	public void login(String name,String passwd);
	
	/**
	 * 游戏打boss
	 */
	public void killBoss();
	
	/**
	 * 进行升级
	 */
	public void update();
	
}
