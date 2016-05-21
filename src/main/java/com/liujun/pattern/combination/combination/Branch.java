package com.liujun.pattern.combination.combination;

import java.util.LinkedList;
import java.util.List;

public class Branch extends Corp
{
	
	/**
	 * 子集合
	 */
	private List<Corp> subordinateList = new LinkedList<Corp>();
 
	public Branch(String name, String position, int salary)
	{
		super(name, position, salary);
	}
	
	
	public void addSubordinate(Corp item)
	{
		this.subordinateList.add(item);
	}
	
	public List<Corp> getSubordinate()
	{
		return this.subordinateList;
	}
	
}

