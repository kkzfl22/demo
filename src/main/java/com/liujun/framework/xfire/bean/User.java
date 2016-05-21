package com.liujun.framework.xfire.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class User implements Serializable {

	/**
	 * 用户id信息
	 */
	private int id;

	/**
	 * 用户名
	 */
	private String name;

	/**
	 * 密码
	 */
	private String pwd;

	/**
	 * 结果信息
	 */
	private Boday[] rsp;

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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Boday[] getRsp() {
		return rsp;
	}

	public void setRsp(Boday[] rsp) {
		this.rsp = rsp;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pwd=" + pwd + ", rsp=" + Arrays.toString(rsp) + "]";
	}

}
