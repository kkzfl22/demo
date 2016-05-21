package com.liujun.pattern.command.project;

/**
 * 需求工程师实现
 * @author liujun
 *
 */
public class DemandEngineer implements EngineerInf
{

	@Override
	public void find(String name)
	{
		System.out.println("找到需求工程师:"+name);
	}

	@Override
	public void add(String content)
	{
		System.out.println("添加最新的需求:"+content);
	}

	@Override
	public void update(String content)
	{
		System.out.println("修改最新的需求:"+content);
	}

	@Override
	public void delete(String content)
	{
		System.out.println("最新需求删除:"+content);
	}

	@Override
	public void play(String name)
	{
		System.out.println("根据最新的需求做出计划,当前人:"+name);
	}


}
