package com.liujun.framework.apachecommons;

import org.apache.commons.csv.CSVRecord;

public class UserinfoBean implements ParseCsvDataLineInf<UserinfoBean>
{
	/**
	 * 用户名信息
	 */
	private String username;

	/**
	 * 密码信息
	 */
	private String pwd;

	/**
	 * 年龄信息
	 */
	private String age;

	/**
	 * 名称
	 */
	private String name;

	public UserinfoBean()
	{
	}

	public UserinfoBean(String username, String pwd, String age, String name)
	{
		this.username = username;
		this.pwd = pwd;
		this.age = age;
		this.name = name;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPwd()
	{
		return pwd;
	}

	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}

	public String getAge()
	{
		return age;
	}

	public void setAge(String age)
	{
		this.age = age;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("UserinfoBean [username=");
		builder.append(username);
		builder.append(", pwd=");
		builder.append(pwd);
		builder.append(", age=");
		builder.append(age);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public String[] parseCsvData()
	{
		return new String[] { username, pwd, name, age };
	}

	@Override
	public UserinfoBean toDataBean(CSVRecord lineMap)
	{
		UserinfoBean user = new UserinfoBean();

		if (lineMap != null)
		{
			user.setUsername(lineMap.get(0));
			user.setPwd(lineMap.get(1));
			user.setName(lineMap.get(2));
			user.setAge(lineMap.get(3));
		}

		return user;
	}

	@Override
	public String[] getColumn()
	{
		return new String[] { "用户名", "密码", "名称", "年龄" };
	}

}
