package com.liujun.pattern.command.project;

import java.util.List;

/**
 * 进行客户端调用的服务
 * @author liujun
 *
 */
public class InvokeService
{
	/**
	 * 需地执行服务对象
	 */
	private List<AbsCommon> common;
	
	/**
	 * 进行调用
	 */
	public void action()
	{
		if(this.getCommon() != null)
		{
			for (AbsCommon item : common)
			{
				item.exec();
				System.out.println();
			}
		}
	}

	public List<AbsCommon> getCommon()
	{
		return common;
	}

	public void setCommon(List<AbsCommon> common)
	{
		this.common = common;
	}

	
	
}
