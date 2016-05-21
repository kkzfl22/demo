package com.liujun.pattern.command.project;

/**
 * 基本的操作流程
 * @author liujun
 *
 */
public interface EngineerInf
{
	/**
	 * 进行查找
	 */
	public void find(String name);
	
	/**
	 * 增加内容
	 */
	public void add(String content);
	
	/**
	 * 进行修改
	 */
	public void update(String content);
	
	/**
	 * 进行删除
	 */
	public void delete(String content);
	
	/**
	 * 进行计划
	 */
	public void play(String name);
}
