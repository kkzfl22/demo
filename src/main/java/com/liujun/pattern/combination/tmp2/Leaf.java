package com.liujun.pattern.combination.tmp2;

public class Leaf implements ILeaf
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
	 * 薪资
	 */
	private int salary;

	public Leaf(String name, String position, int salary)
	{
		this.name = name;
		this.position = position;
		this.salary = salary;
	}

	@Override
	public String getInfo()
	{
		StringBuilder show = new StringBuilder();

		show.append("name").append(name).append(",");
		show.append("position").append(position).append(",");
		show.append("salary").append(salary).append("\r\n");

		return show.toString();
	}

}
