package com.liujun.pattern.backup.tmp1;

public class TestMain
{
	public static void main(String[] args)
	{
		Boy boy = new Boy();

		// 初始化心情
		boy.setState("心情很好");

		System.out.println("开始追上女孩子。。");
		print(boy);

		// 记录下当前的心情
		Boy backup = new Boy();

		backup.setState(boy.getState());

		// 进行开始追求
		boy.changState();
		System.out.println("追求失败....");
		print(boy);

		// 失败后再战，将心情还原
		boy.setState(backup.getState());

		System.out.println("哪里推倒哪里爬起来");
		print(boy);
	}

	private static void print(Boy boy)
	{
		System.out.println("当前的心情:" + boy.getState());
	}
}
