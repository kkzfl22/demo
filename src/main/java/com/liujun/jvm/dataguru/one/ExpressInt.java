package com.liujun.jvm.dataguru.one;

public class ExpressInt {

	public static void printInt(int value)
	{
		for (int i = 0; i < 32; i++) {
			int t = (value&0x80000000 >>> i) >>> (31-i);
			System.out.print(t);
			
		}
	}
	
	
	public static void main(String[] args) {
		ExpressInt.printInt(205);
	}
	
}
