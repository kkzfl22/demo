package com.liujun.util;

public class FloatToTwo
{
	public static void main(String[] args)
	{
		String floatNum = "100.2";
		
		//、十进制转二进制(32位)，整型部分除2取余，小数部分成2取整。
		int numVal = Integer.parseInt(floatNum.substring(0,floatNum.indexOf(".")));
		int floatVal = Integer.parseInt(floatNum.substring(floatNum.indexOf(".")+1));
		
		
		System.out.println("num"+numVal);
		System.out.println("floatStr"+floatVal);
		
		
		int numFloat = 0;
		String floatStr = "";
		
		while(numVal/2 > 0)
		{
			numFloat = numVal % 2;
			floatStr = numFloat + floatStr;
			numVal =  numVal / 2;
		}
		
		System.out.println(floatStr);
		
	}
}
