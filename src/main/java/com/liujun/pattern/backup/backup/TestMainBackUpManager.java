package com.liujun.pattern.backup.backup;

public class TestMainBackUpManager
{
	public static void main(String[] args)
	{
		//男孩对象
		Boy boy = new Boy();
		
		//状态管理对象
		BackUpManager manage = new BackUpManager();
		
		
		//初始化男孩子追求女孩
		boy.setState("刚开始追，信心满满的。。");
		
		print(boy);
		
		//记录状态
		manage.setBackup(boy.recordState(boy.getState()));
		
		//改变开始进行追求
		boy.change();
		
		print(boy);
		
		//还原状态
		boy.resetState(manage.getBackup());

		System.out.println("失败后再来的状态:");
		print(boy);
	}
	
	private static void print(Boy boy)
	{
		System.out.println("当前的心情："+boy.getState());
	}
}
