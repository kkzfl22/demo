package com.liujun.io.seqWriteFile;

import java.io.Serializable;

public class Base implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 名字
	 */
	private String name;

	
	/**
	 * 年龄
	 */
	private int age;
	
	
	/**
	 * 地址信息
	 */
	private String address;


	public Base(String name, int age, String address)
	{
		this.name = name;
		this.age = age;
		this.address = address;
	}


	public String getName()
	{
		return name;
	}


	public void setName(String name)
	{
		this.name = name;
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


	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}


	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Base [name=");
		builder.append(name);
		builder.append(", age=");
		builder.append(age);
		builder.append(", address=");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}
	
	
}
