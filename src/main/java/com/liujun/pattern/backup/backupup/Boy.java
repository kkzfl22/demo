package com.liujun.pattern.backup.backupup;


public class Boy extends Base
{

	/**
	 * 当前年龄信息
	 */
	private int age;

	/**
	 * 地址信息
	 */
	private String address;

	public Boy(String name, int age, String address)
	{
		super(name);
		this.age = age;
		this.address = address;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public Memento createMemento()
	{
		return new Memento(BeanUtils.getBeanValue(this));
	}

	public void resetMemento(Memento backup)
	{
		BeanUtils.setBeanValue(this, backup.getMap());
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("Boy [age=");
		builder.append(age);
		builder.append(", address=");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}

}
