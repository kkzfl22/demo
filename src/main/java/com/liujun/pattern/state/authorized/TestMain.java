package com.liujun.pattern.state.authorized;

public class TestMain
{
	public static void main(String[] args)
	{
		// 1,针对同一文件，拥有不同文件，可以有不同的操作
		
		//仅为读取的权限
		FileContext context = new FileContext();

		context.read();

		context.write();

		context.upName();
		
		//全流程权限
		context.setCurrAuthState(context.getAll());
		
		context.read();

		context.write();

		context.upName();

	}
}
