package com.liujun.pattern.command.project;

/**
 * 代码工程师实现
 * @author liujun
 *
 */
public class CodeEngineer implements EngineerInf
{

	@Override
	public void find(String name)
	{
		System.out.println("找到码农:"+name);
	}

	@Override
	public void add(String content)
	{
		System.out.println("根据需求与美工的页面添加功能："+content);
	}

	@Override
	public void update(String content)
	{
		System.out.println("根据需求与美工的页面修改功能："+content);
	}

	@Override
	public void delete(String content)
	{
		System.out.println("根据需求与美工的页面删除功能："+content);
	}

	@Override
	public void play(String name)
	{
		System.out.println("根据需求与美工的页面，做出最新的编码计划，人："+name);
	}

}
