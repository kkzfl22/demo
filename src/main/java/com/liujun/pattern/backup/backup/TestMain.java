package com.liujun.pattern.backup.backup;

public class TestMain
{
	public static void main(String[] args)
	{
		Boy boy = new Boy();
		
		boy.setState("当前刚开始追女孩子，心情非常好");
		
		print(boy);
		
		Backup backup = boy.recordState(boy.getState());
		
		System.out.println("追求失败");
		boy.change();
		
		print(boy);
		
		boy.resetState(backup);
		System.out.println("哪里跌倒的哪里爬起来，再战!");
		print(boy);
		
		
		
	}
	
	private static void print(Boy boy)
	{
		System.out.println("当前的心情："+boy.getState());
	}
}
