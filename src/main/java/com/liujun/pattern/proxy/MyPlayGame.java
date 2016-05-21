package com.liujun.pattern.proxy;

/**
 * 自己打游戏实现
 * @author liujun
 *
 */
public class MyPlayGame implements IplayGame
{

	/**
	 * 帐号
	 */
	private String name;
	
	@Override
	public void login(String name, String passwd)
	{
		this.name = name;
		System.out.println("帐号:"+name+";密码:"+passwd+";登陆成功!");
	}

	@Override
	public void killBoss()
	{
		System.out.println("帐号:"+name+";正在进行打boss");
	}

	@Override
	public void update()
	{
		System.out.println("帐号:"+name+"升级...");
	}

}
