package com.liujun.pattern.access;

/**
 * 普通员工
 * @author liujun
 *
 */
public class CommonEmployee extends Employee
{

	/**
	 * 普通员工的工作
	 */
	private String job;
	
	
	
	public String getJob()
	{
		return job;
	}



	public void setJob(String job)
	{
		this.job = job;
	}

	

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("CommonEmployee [job=");
		builder.append(job);
		builder.append("]");
		return builder.toString();
	}



	@Override
	public void accept(Ivisitor visitor)
	{
		//将当前的普通员工对名象传递给访问者，由访问者决定何时调用
		visitor.visitorEmpoyee(this);
	}

}
