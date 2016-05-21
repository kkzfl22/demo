package com.liujun.pattern.combination.tmp1;

import java.util.ArrayList;
import java.util.List;

public class Root implements IRoot
{
	
	@SuppressWarnings("rawtypes")
	private List subordinateInfo = new ArrayList();
	
	/**
	 * 名字
	 */
	private String name;

	/**
	 * 职位
	 */
	private String postion;
	
	/**
	 * 
	 */
	private int salary;
	
	
	
	public Root(String name, String postion, int salary)
	{
		this.name = name;
		this.postion = postion;
		this.salary = salary;
	}

	@Override
	public String getInfo()
	{
		StringBuilder show = new StringBuilder();
		
		show.append("name").append(name).append(",");
		show.append("postion").append(postion).append(",");
		show.append("salary").append(salary).append("\r\n");
		
		return show.toString();
	}

	@Override
	public void add(IBranch branch)
	{
		this.subordinateInfo.add(branch);
	}

	@Override
	public void add(ILeaf leaf)
	{
		this.subordinateInfo.add(leaf);
	}

	@Override
	public List getSubordinateInfo()
	{
		return this.subordinateInfo;
	}

}
