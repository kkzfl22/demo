package com.liujun.pattern.access;

import java.util.ArrayList;
import java.util.List;

/**
 * 秘书访问接口
 * 
 * 秘书关心所有信息，需要进行准备
 * 
 * @author liujun
 * 
 */
public class ClerkVisitor implements Ivisitor, PrintInfo
{

	/**
	 * 员工信息
	 */
	private List<CommonEmployee> employList = new ArrayList<CommonEmployee>();

	/**
	 * 秘书也关心管理者的信息
	 */
	private List<Manager> managerList = new ArrayList<Manager>();

	@Override
	public void visitorEmpoyee(CommonEmployee employee)
	{
		this.employList.add(employee);
	}

	@Override
	public void visitorManager(Manager manage)
	{
		managerList.add(manage);
	}

	@Override
	public void print()
	{
		System.out.println("当前为秘书，关心所有的信息，以便进行报表");
		System.out.println("打印当前员工的信息");
		for (CommonEmployee employee : employList)
		{
			System.out.println(employee);
		}

		System.out.println("打印管理层信息");
		for (Manager manager : managerList)
		{
			System.out.println(manager);
		}
	}

	@Override
	public int count()
	{
		System.out.println("秘书关心管理层的工资信息");

		int value = 0;

		for (Manager manage : managerList)
		{
			value += manage.getSalary();
		}
		
		for (CommonEmployee manage : employList)
		{
			value += manage.getSalary();
		}
		return value;
	}

}
