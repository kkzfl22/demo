package com.liujun.io.seqWriteFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 进行序列化的bean对象信息
 * 
 * @author liujun
 * 
 */
public class BeanInfo extends Base implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 身高
	 */
	private int hight;

	/**
	 * 职位
	 */
	private String option;

	/**
	 * 对象
	 */
	private List<String> list = new ArrayList<String>();

	public BeanInfo(String name, int age, String address, String sex, int hight, String option)
	{
		super(name, age, address);
		this.sex = sex;
		this.hight = hight;
		this.option = option;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public int getHight()
	{
		return hight;
	}

	public void setHight(int hight)
	{
		this.hight = hight;
	}

	public String getOption()
	{
		return option;
	}

	public void setOption(String option)
	{
		this.option = option;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public List<String> getList()
	{
		return list;
	}

	public void setList(List<String> list)
	{
		this.list = list;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("BeanInfo [sex=");
		builder.append(sex);
		builder.append(", hight=");
		builder.append(hight);
		builder.append(", option=");
		builder.append(option);
		builder.append(", list=");
		if (null != list)
		{
			builder.append(list.size());
		}
		else
		{
			builder.append("null");
		}
		builder.append("]");
		return builder.toString();
	}

}
