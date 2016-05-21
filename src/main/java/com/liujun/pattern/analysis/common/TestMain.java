package com.liujun.pattern.analysis.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TestMain
{
	public static void main(String[] args) throws IOException
	{
		// 1,得到表达式
		String expStr = getExpStr();
		
		//得到表达式的参数值
		Map<String,Integer> valueMap =  getValue(expStr);
		
		Calculator cal = new Calculator(expStr);
		
		int value = cal.runExec(valueMap);
		
		System.out.println("最终的计算结果:"+value);
	}
	
	
	
	public static String getExpStr() throws IOException
	{
		System.out.print("请输入计算的表达式:");
		
		String value = null;

		InputStreamReader inputRead = null;
		BufferedReader buffer = null;
		try
		{

			inputRead = new InputStreamReader(System.in);
			buffer = new BufferedReader(inputRead);

			value = buffer.readLine();
		} catch (IOException e)
		{
			e.printStackTrace();
			throw e;
		} 

		return value;
	}
	
	
	public static Map<String,Integer> getValue(String expStr) throws IOException
	{
		Map<String,Integer> result = new HashMap<String, Integer>();
		
		for (char expChar : expStr.toCharArray())
		{
			if(expChar != '+' && expChar != '-' )
			{
				if(!result.containsKey(String.valueOf(expChar)))
				{
					System.out.print("请输入"+expChar+"的值:");
					String itemValue = new BufferedReader(new InputStreamReader(System.in)).readLine();
					result.put(String.valueOf(expChar), Integer.parseInt(itemValue));
				}
			}
		}
		
		return result;
	}
	
	@Deprecated
	public void test()
	{
		
	}

}
