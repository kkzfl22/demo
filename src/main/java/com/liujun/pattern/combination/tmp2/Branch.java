package com.liujun.pattern.combination.tmp2;

import java.util.ArrayList;
import java.util.List;

public class Branch implements IBranch
{

	private List<ICorp> list = new ArrayList<ICorp>();

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

	public Branch(String name, String position, int salary)
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

	@Override
	public void add(ICorp corp)
	{
		this.list.add(corp);
	}

	@Override
	public List<ICorp> getSubordinateInfo()
	{
		return this.list;
	}

}
