package com.som.user.userjdbc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.som.user.userjdbc.bean.Userinfo;
import com.som.user.userjdbc.dao.JdbcBase;
import com.som.user.userjdbc.dao.ParseBeanInf;
import com.som.user.userjdbc.dao.UserDaoInf;

/**
 * 用户数据库操作实现
 * 
 * @author liujun
 * @date 2016年4月19日
 * @verion 0.0.1
 */
public class UserDaoImpl extends JdbcBase<Userinfo>implements UserDaoInf
{

	@Override
	public int addUser(Userinfo adduser) throws SQLException
	{
		String sql = "insert into userinfo(username,passwd,sex,mobile,age,hight,address) values(?,?,?,?,?,?,?)";

		LinkedHashMap<String, Object> param = new LinkedHashMap<String, Object>();

		param.put("username", adduser.getUsername());
		param.put("passwd", adduser.getPassword());
		param.put("sex", adduser.getSex());
		param.put("mobile", adduser.getMobile());
		param.put("age", adduser.getAge());
		param.put("hight", adduser.getHight());
		param.put("address", adduser.getAddress());

		return this.upd(sql, param);
	}

	@Override
	public Userinfo userLogin(Userinfo user) throws SQLException
	{
		String sql = "select username from userinfo where username = ? and passwd = ?";

		LinkedHashMap<String, Object> param = new LinkedHashMap<String, Object>();

		param.put("username", user.getUsername());
		param.put("passwd", user.getPassword());

		return this.queryForOne(sql, param, new ParseBeanInf<Userinfo>()
		{
			@Override
			public Userinfo parse(ResultSet rs) throws SQLException
			{
				Userinfo us = new Userinfo();
				us.setUsername(rs.getString("username"));

				return us;
			}

		});

	}

	@Override
	public Userinfo checkUserName(String name) throws SQLException
	{
		String sql = "select username from userinfo where username = ? ";

		LinkedHashMap<String, Object> param = new LinkedHashMap<String, Object>();

		param.put("username", name);

		return this.queryForOne(sql, param, new ParseBeanInf<Userinfo>()
		{
			@Override
			public Userinfo parse(ResultSet rs) throws SQLException
			{
				Userinfo us = new Userinfo();
				us.setUsername(rs.getString("username"));

				return us;
			}

		});
	}

}
