package com.liujun.pattern.access;

import java.util.ArrayList;
import java.util.List;

public class TestMain
{
	public static void main(String[] args)
	{
		// 创建Boss信息
		// PrintInfo bossVisitor = new BossVisitor();
		PrintInfo bossVisitor = new ClerkVisitor();

		for (Employee employ : mockEmployee())
		{
			employ.accept(bossVisitor);
		}
		// 老板所需信息输出
		bossVisitor.print();
		
		//输出薪资信息
		System.out.println(bossVisitor.count());
	}

	// 模拟出公司的人员情况，我们可以想象这个数据是通过持久层传递过来的
	public static List<Employee> mockEmployee()
	{
		
		List<Employee> empList = new ArrayList<Employee>();
		// 产生张三这个员工
		CommonEmployee zhangSan = new CommonEmployee();
		zhangSan.setJob("编写Java程序，绝对的蓝领、苦工加搬运工");
		zhangSan.setName("张三");
		zhangSan.setSalary(1800);
		empList.add(zhangSan);
		// 产生李四这个员工
		CommonEmployee liSi = new CommonEmployee();
		liSi.setJob("页面美工，审美素质太不流行了！");
		liSi.setName("李四");
		liSi.setSalary(1900);
		empList.add(liSi);
		// 再产生一个经理
		Manager wangWu = new Manager();
		wangWu.setName("王五");
		wangWu.setPerformance("基本上是负值，但是我会拍马屁呀");
		wangWu.setSalary(18750);
		empList.add(wangWu);
		return empList;
	}
}
