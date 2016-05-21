package com.liujun.framework.springsecurity.bean;

import java.util.List;

/**
 * 我的用户角色信息
 * 
 * @author Administrator
 * 
 */
public class MyRole {

	/**
	 * 用户角色id
	 */
	private int id;

	/**
	 * 当前的角色名称
	 */
	private String roleName;

	/**
	 * 当前角色描述
	 */
	private String descn;

	/**
	 * 当前的资源集合
	 */
	private List<MyResource> resourceS;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	public List<MyResource> getResourceS() {
		return resourceS;
	}

	public void setResourceS(List<MyResource> resourceS) {
		this.resourceS = resourceS;
	}

	@Override
	public String toString() {
		return "MyRole [id=" + id + ", roleName=" + roleName + ", descn=" + descn + ", resourceS=" + resourceS + "]";
	}

}
