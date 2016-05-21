package com.liujun.pattern.combination.tmp1;


public class Leaf implements ILeaf
{
	
	
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
	
	
	
	public Leaf(String name, String postion, int salary)
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


}
