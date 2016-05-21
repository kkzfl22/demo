package com.liujun.pattern.access;

import java.util.ArrayList;
import java.util.List;

/**
 * 老总访问接口
 * 
 * 老总关心管理者的业绩信息，对于普通员工则不关心
 * 
 * @author liujun
 * 
 */
public class BossVisitor implements Ivisitor, PrintInfo
{

	/**
	 * 秘书也关心管理者的信息
	 */
	private List<Manager> managerList = new ArrayList<Manager>();

	@Override
	public void visitorEmpoyee(CommonEmployee employee)
	{
	}

	@Override
	public void visitorManager(Manager manage)
	{
		this.managerList.add(manage);
	}

	@Override
	public void print()
	{
		System.out.println("老总，只关心业绩信息");
		System.out.println("打印管理层信息");
		for (Manager manager : managerList)
		{
			System.out.println(manager);
		}

	}

	@Override
	public int count()
	{
		System.out.println("老总关心管理层的工资信息");
		
		int value = 0;
		
		for (Manager manage : managerList)
		{
			value += manage.getSalary();
		}
		
		
		return value;
	}

}
