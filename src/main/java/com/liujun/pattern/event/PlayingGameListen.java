package com.liujun.pattern.event;

import java.util.Date;

/**
 * 当前是一个正在玩游戏的同学，当老师来了，就要停止玩游戏
 * @author liujun
 *
 * @date 2015年5月5日
 * @vsersion 0.0.1
 */
public class PlayingGameListen {
	
	public void startPlayingGame()
	{
		System.out.println("当前我正在开始玩游戏，开始时间:"+new Date().toLocaleString());
	}
	
	public void stopPlayingGame(Date time)
	{
		System.out.println("老师来了，我要停止玩游戏:"+time.toLocaleString());
	}

}
