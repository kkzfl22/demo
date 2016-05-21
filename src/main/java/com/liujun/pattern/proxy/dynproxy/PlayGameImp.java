package com.liujun.pattern.proxy.dynproxy;

public class PlayGameImp implements IplayGame
{
	/**
	 * 名称
	 */
	private String name;

	@Override
	public void killBoss()
	{
		System.out.println(name + "在杀boss");
	}

	@Override
	public void login(String name)
	{
		this.name = name;
		System.out.println("登陆:" + name);

	}

}
