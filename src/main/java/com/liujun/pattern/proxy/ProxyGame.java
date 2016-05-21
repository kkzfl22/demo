package com.liujun.pattern.proxy;

public class ProxyGame implements IplayGame
{
	
	/**
	 * 进行游戏的对象
	 */
	private IplayGame playGame;
	
	public ProxyGame(IplayGame playGame)
	{
		this.playGame = playGame;
	}

	@Override
	public void login(String name, String passwd)
	{
		this.playGame.login(name, passwd);
	}

	@Override
	public void killBoss()
	{
		this.playGame.killBoss();
	}

	@Override
	public void update()
	{
		this.playGame.update();
	}
	
	
	public void getMoney()
	{
		System.out.println("一次升级完成，收费￥200");
	}
	
	
}
