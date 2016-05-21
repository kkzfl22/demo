package com.liujun.pattern.combination.combination;

public abstract class Corp
{
	/**
	 * 名字
	 */
	private String name;
	
	/**
	 * 职位
	 */
	private String position;
	
	/**
	 * 薪水
	 */
	private int salary;

	public Corp(String name, String position, int salary)
	{
		this.name = name;
		this.position = position;
		this.salary = salary;
	}
	
	public String getInfo()
	{
		StringBuilder show = new StringBuilder();

		show.append("name").append(name).append(",");
		show.append("position").append(position).append(",");
		show.append("salary").append(salary).append(";\r\n");

		return show.toString();
	}
	
}
