package com.liujun.pattern.factory.delayproductfactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 延迟加载工厂实现
 * @author liujun
 *
 */
public class DelayProductFacory
{
	/**
	 * 用于存放实例的工厂对象信息
	 */
	private static final ConcurrentHashMap<String,HumanInf> INSTALL_MAP = new ConcurrentHashMap<String,HumanInf>();
	
	public static HumanInf getHumanObj(String type)
	{
		if(INSTALL_MAP.contains(type))
		{
			return INSTALL_MAP.get(type);
		}
		else
		{
			//如果是创建黑人
			if("black".equals(type))
			{
				HumanInf black = new BlackHumanImpl();
				INSTALL_MAP.putIfAbsent("black",black);
			}
			
			//如果为创建白人
			else if("white".equals(type))
			{
				HumanInf white = new WhiteHumanImpl();
				INSTALL_MAP.putIfAbsent("white",white);
			}
			
			//如果为创建黑人
			else if("yellow".equals(type))
			{
				HumanInf yellow = new YellowHumanImpl();
				INSTALL_MAP.putIfAbsent("yellow",yellow);
			}
			
			return INSTALL_MAP.get(type);
		}
		
		
	}
}
