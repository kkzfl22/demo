package com.liujun.framework.springsecurity.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.liujun.framework.springsecurity.bean.MyRole;
import com.liujun.framework.springsecurity.bean.MyUser;

public interface MyRoleServiceInf {
	
	/**
	 * 加载所有的权限信息
	 * @return
	 * @throws DataAccessException
	 */
	public List<MyRole> queryRoles();

	
	/**
	 * 加载当前用户的权限信息
	 * @return
	 * @throws DataAccessException
	 */
	public MyUser getRoleByUserId(MyUser user);



}
