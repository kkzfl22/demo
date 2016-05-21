package com.som.user.userjdbc.bean;

/**
 * 用户信息
 * 
 * @author liujun
 * @date 2016年4月18日
 * @verion 0.0.1
 */
public class Userinfo
{
	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 性别,1，男，2，女
	 */
	private int sex;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 年龄
	 */
	private int age;

	/**
	 * 身高
	 */
	private int hight;

	/**
	 * 地址
	 */
	private String address;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public int getSex()
	{
		return sex;
	}

	public void setSex(int sex)
	{
		this.sex = sex;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public int getHight()
	{
		return hight;
	}

	public void setHight(int hight)
	{
		this.hight = hight;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Userinfo [username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", age=");
		builder.append(age);
		builder.append(", hight=");
		builder.append(hight);
		builder.append(", address=");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}

}
