package com.som.user.userjdbc.service;

import com.som.user.userjdbc.bean.Userinfo;

public interface UserServiceInf
{

	/**
	 * 添加用户
	 * 
	 * @param adduser
	 * @return true 添加成功
	 */
	public boolean addUser(Userinfo adduser);

	/**
	 * 用户登陆
	 * 
	 * @param user
	 * @return true 登陆成功
	 */
	public boolean userLogin(Userinfo user);

	/**
	 * 检查用户名是否存在
	 * 
	 * @param name
	 * @return true 代表用户已经存在
	 */
	public boolean checkUserName(String name);

}
