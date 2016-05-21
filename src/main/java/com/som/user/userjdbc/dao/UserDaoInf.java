package com.som.user.userjdbc.dao;

import java.sql.SQLException;

import com.som.user.userjdbc.bean.Userinfo;

/**
 * 用户数据库操作
 * 
 * @author liujun
 * @date 2016年4月18日
 * @verion 0.0.1
 */
public interface UserDaoInf
{
	/**
	 * 添加用户
	 * 
	 * @param adduser
	 * @return
	 * @throws SQLException
	 */
	public int addUser(Userinfo adduser) throws SQLException;

	/**
	 * 用户登陆
	 * 
	 * @param user
	 * @return > 0 代表登陆成功
	 * @throws SQLException
	 */
	public Userinfo userLogin(Userinfo user) throws SQLException;

	/**
	 * 检查用户名是否存在
	 * 
	 * @param name
	 * @return null 代表不存在，不为null代表存在
	 * @throws SQLException
	 */
	public Userinfo checkUserName(String name) throws SQLException;

}
