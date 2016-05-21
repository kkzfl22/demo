package com.liujun.pattern.flyweight.pattern;

import java.util.HashMap;
import java.util.Map;

public class SignFactory
{
	private Map<String, SignInfo> signMapObj = new HashMap<String, SignInfo>();

	@Deprecated
	public SignInfo getSignInfo()
	{
		return new SignInfo();
	}

	public SignInfo getSignInfo(String key)
	{
		SignInfo result = null;

		if (signMapObj.containsKey(key))
		{
			System.out.println("直接从对象池中获取");
			return signMapObj.get(key);
		} else
		{
			result = new SignInfoPool();
			System.out.println("创建对象再获取");
			signMapObj.put(key, result);
		}

		return result;
	}
}
