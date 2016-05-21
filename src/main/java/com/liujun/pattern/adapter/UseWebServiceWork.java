package com.liujun.pattern.adapter;

import java.util.HashMap;
import java.util.Map;

public class UseWebServiceWork
{
	public Map<String, String> getUserWork()
	{
		Map<String, String> map = new HashMap<String,String>();

		map.put("work", "软件开发");

		return map;
	}
}
