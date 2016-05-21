package com.liujun.pattern.builder.carbuild;

import java.util.ArrayList;
import java.util.List;

public abstract class CarModel
{
	/**
	 * 用来定义车的运行顺序
	 */
	private List<String> START_SEQ = new ArrayList<String>();
	
	/**
	 * 启动汽车
	 */
	protected abstract void start();

	/**
	 * 停车
	 */
	protected abstract void stop();
	
	/**
	 * 按喇叭
	 */
	protected abstract void alarm();
	
	/**
	 * 引擎发出声音
	 */
	protected abstract void engineBoom();
	
	public final void run()
	{
		for (String item : START_SEQ)
		{
			if("start".equalsIgnoreCase(item))
			{
				this.start();
			}
			else if("stop".equalsIgnoreCase(item))
			{
				this.stop();
			}
			else if("alarm".equalsIgnoreCase(item))
			{
				this.alarm();
			}
			else if("engineBoom".equalsIgnoreCase(item))
			{
				this.engineBoom();
			}
		}
	}
	
	protected void setSeq(List<String> seq)
	{
		this.START_SEQ = seq;
	}
	
	
}
