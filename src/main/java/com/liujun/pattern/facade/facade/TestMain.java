package com.liujun.pattern.facade.facade;

public class TestMain
{
	public static void main(String[] args)
	{
		ModenPostOffice modepost = new ModenPostOffice();
		
		modepost.sendLetter("这是发送的地址", "信件内容发送信息");
		
		
	}
}
