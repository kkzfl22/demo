package com.som.user.userjdbc.service.impl;

import java.sql.SQLException;

import com.som.user.userjdbc.bean.Userinfo;
import com.som.user.userjdbc.dao.UserDaoInf;
import com.som.user.userjdbc.dao.impl.UserDaoImpl;
import com.som.user.userjdbc.service.UserServiceInf;

public class UserServiceImp implements UserServiceInf
{

	/**
	 * 用户操作数据库实现
	 */
	private UserDaoInf userDao = new UserDaoImpl();

	@Override
	public boolean addUser(Userinfo adduser)
	{
		// 1,先检查用户名是否已经存
		boolean chekcRsp = this.checkUserName(adduser.getUsername());
		
		//如果用户名不存在，则进行添加操作
		if (!chekcRsp)
		{
			int addrsp = -1;

			try
			{
				addrsp = userDao.addUser(adduser);
			} catch (SQLException e)
			{
				e.printStackTrace();
			}

			if (addrsp != -1)
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean userLogin(Userinfo user)
	{
		Userinfo userLognRsp = null;
		try
		{
			userLognRsp = userDao.userLogin(user);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		if (null != userLognRsp && userLognRsp.getUsername() != null)
		{
			return true;
		}

		return false;
	}

	@Override
	public boolean checkUserName(String name)
	{
		Userinfo userLognRsp = null;
		try
		{
			userLognRsp = userDao.checkUserName(name);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		if (null != userLognRsp && userLognRsp.getUsername() != null)
		{
			return true;
		}

		return false;
	}

}
