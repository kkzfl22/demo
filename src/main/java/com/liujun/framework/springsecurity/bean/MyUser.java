package com.liujun.framework.springsecurity.bean;

import java.util.List;
import java.util.Set;

/**
 * 用户信息
 * @author Administrator
 *
 */
public class MyUser {
	
	/**
	 * id
	 */
	private int id;
	
	
	/**
	 * 用户姓名
	 */
	private String name;
	
	
	/**
	 * 密码
	 */
	private String password;
	
	
	/**
	 * 状态
	 */
	private int status;
	
	
	/**
	 * 描述
	 */
	private String descn;
	
	/**
	 * 用户的权限集合
	 */
	private List<MyRole> userRoles;


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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getDescn() {
		return descn;
	}


	public void setDescn(String descn) {
		this.descn = descn;
	}


	public List<MyRole> getUserRoles() {
		return userRoles;
	}


	public void setUserRoles(List<MyRole> userRoles) {
		this.userRoles = userRoles;
	}


	@Override
	public String toString() {
		return "MyUser [id=" + id + ", name=" + name + ", password=" + password + ", status=" + status + ", descn=" + descn + ", userRoles=" + userRoles + "]";
	}


}
