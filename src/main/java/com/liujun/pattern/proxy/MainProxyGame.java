package com.liujun.pattern.proxy;

public class MainProxyGame
{
	public static void main(String[] args)
	{
		IplayGame game = new MyPlayGame();
		
		ProxyGame proxy = new ProxyGame(game);
		
		proxy.login("jim", "jim");
		
		proxy.killBoss();
		
		proxy.update();
	}
}
