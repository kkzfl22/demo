package com.liujun.pattern.command.project;

/**
 * 美工实现
 * 
 * @author liujun
 * 
 */
public class ArtEngineer implements EngineerInf
{

	@Override
	public void find(String name)
	{
		System.out.println("找到美工:" + name);
	}

	@Override
	public void add(String content)
	{
		System.out.println("添加页面:" + content);
	}

	@Override
	public void update(String content)
	{
		System.out.println("修改页面:" + content);
	}

	@Override
	public void delete(String content)
	{
		System.out.println("删除页面:" + content);
	}

	@Override
	public void play(String name)
	{
		System.out.println("根据最新的需求做出计划,名称:" + name);
	}

}
