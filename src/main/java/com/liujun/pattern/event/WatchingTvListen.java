package com.liujun.pattern.event;

import java.util.Date;

/**
 * 当前是一个正在看电视的同学，老师来了，就要停止看电视了
 * @author liujun
 *
 * @date 2015年5月5日
 * @vsersion 0.0.1
 */
public class WatchingTvListen {
	
	public void startWatchingTV()
	{
		System.out.println("我正在看电视，电视节目还可以，开始时间:"+new Date().toLocaleString());
	}
	
	public void stopWatchingTV(Date time)
	{
		System.out.println("老师来了，马上停止看电视:"+time.toLocaleString());
	}

}
