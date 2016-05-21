package com.liujun.pattern.adapter;

import java.util.HashMap;
import java.util.Map;

public class UserWebServiceHome
{
	public Map<String,String> getUserHome()
	{
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("home", "上海市徐汇区");		
		
		return map;
	}
}