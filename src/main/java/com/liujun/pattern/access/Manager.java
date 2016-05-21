package com.liujun.pattern.access;

/**
 * 管理层
 * @author liujun
 *
 */
public class Manager extends Employee
{
	
	/**
	 * 业务信息
	 */
	private String performance;
	
	

	public String getPerformance()
	{
		return performance;
	}



	public void setPerformance(String performance)
	{
		this.performance = performance;
	}
	


	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("Manager [performance=");
		builder.append(performance);
		builder.append("]");
		return builder.toString();
	}



	@Override
	public void accept(Ivisitor visitor)
	{
		//将当前的管理者对象传递给访问者，让访问者可以对其进行操作
		visitor.visitorManager(this);
	}

}
