package com.liujun.pattern.builder.builder;

/**
 * 工人建造房子
 * @author liujun
 *
 * @date 2015年1月30日
 * @vsersion 0.0.1
 */
public class Worker implements Builder {
	
	private String window,foor;

	@Override
	public void makeWindow() {
		System.out.println("建造windows");
		window="over order";
	}

	@Override
	public void makefoor() {
		System.out.println("铺设地板");
		foor= "over foor";
	}

	@Override
	public Room getRoom() {
		
		//检查当前房子是否已经建造完成
		if(null != this.window && foor != null)
		{
			System.out.println("房子完成！");
			return new Room();
		}
		
		return null;
	}

}
