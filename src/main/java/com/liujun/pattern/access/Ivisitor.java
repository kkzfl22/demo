package com.liujun.pattern.access;

/**
 * 方问者对象
 * @author liujun
 *
 */
public interface Ivisitor
{
	/**
	 * 访问普通员工
	 * @param employee
	 */
	public void visitorEmpoyee(CommonEmployee employee);
	
	/**
	 * 管理层访问
	 * @param manage
	 */
	public void visitorManager(Manager manage);
}
