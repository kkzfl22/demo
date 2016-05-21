package com.liujun.pattern.backup.backupup;

public class TestMain
{
	public static void main(String[] args)
	{
		//1,创建一个boy对象
		Boy boy = new Boy("张三",12,"上海市徐汇区");
		
		System.out.println("当前备份对象:"+boy);
		
		//创建备份管理员
		BackupManager backManager = new BackupManager();
		
		//1,创建一个备份
		backManager.addMemento("0001",boy.createMemento());
		
		//修改属性值
		boy.setAddress("上海市闵行区");
		boy.setAge(21);
		System.out.println("修改第一次对象:"+boy);
		
		//创建第二次备份
		backManager.addMemento("0002", boy.createMemento());
		
		
		boy.setAddress("上海市宝山区");
		boy.setAge(43);
		boy.setName("张三22");
		System.out.println("修改第二次对象:"+boy);
		
		//第三次备份
		backManager.addMemento("0003", boy.createMemento());
		
		//进行恢复
		boy.resetMemento(backManager.getMemento("0001"));
		
		System.out.println("进行恢复第一次:"+boy);
		
		boy.resetMemento(backManager.getMemento("0002"));
		
		System.out.println("进行恢复第二次:"+boy);
		
		boy.resetMemento(backManager.getMemento("0003"));
		
		System.out.println("进行恢复第三次:"+boy);
		
	}
}
