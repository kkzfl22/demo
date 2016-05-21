package com.liujun.pattern.event;

import java.util.Date;

public class TestEvent {
	
	public static void main(String[] args) throws InterruptedException {
		//1,创建一个尽心的放哨的同学
		Notifier notiy = new GoodNotifier();
		
		//创建玩游戏的同学
		PlayingGameListen playGame = new PlayingGameListen();
		
		//创建看电视的同学
		WatchingTvListen watchTv = new WatchingTvListen();
		
		//玩游戏的同学告诉放哨的同学,老师来了，我停止玩游戏
		notiy.addListen(EventType.TEACHER_COME,playGame, "stopPlayingGame", new Date());
		
		//玩游戏的同学告诉放哨的同学,老师来了，我停止玩游戏
		notiy.addListen(EventType.TEACHER_GO,playGame, "startPlayingGame", new Object[0]);
		
		//看电视的同学告诉放哨的同学，老师来了，我停止看电视
		notiy.addListen(EventType.TEACHER_COME,watchTv, "stopWatchingTV", new Date());
		
		//看电视的同学告诉放哨的同学，老师来了，我停止看电视
		notiy.addListen(EventType.TEACHER_GO,watchTv, "startWatchingTV", new Object[0]);
		
		//通知所人有，老师来了
		notiy.notifly(EventType.TEACHER_COME);
		
		Thread.sleep(2000);
		
		//通知所人有，老师走了
		notiy.notifly(EventType.TEACHER_GO);
	}

}
