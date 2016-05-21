package com.liujun.framework.springsecurity.bean;

/**
 * 资源信息
 * @author Administrator
 *
 */
public class MyResource {
	
	/**
	 * 主键id
	 */
	private int id;
	
	/**
	 * 名称信息
	 */
	private String name;
	
	/**
	 * 类型信息,如URL
	 */
	private String resType;
	
	/**
	 * 路径信息
	 */
	private String resString;
	
	/**
	 * 用户排序字段
	 */
	private int priority;
	
	/**
	 * 描述
	 */
	private String descn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getResString() {
		return resString;
	}

	public void setResString(String resString) {
		this.resString = resString;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", name=" + name + ", resType=" + resType
				+ ", resString=" + resString + ", priority=" + priority
				+ ", descn=" + descn + "]";
	}
	
}
