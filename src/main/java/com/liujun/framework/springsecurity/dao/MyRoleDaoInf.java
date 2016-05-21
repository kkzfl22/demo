package com.liujun.framework.springsecurity.dao;

import java.util.List;

import com.liujun.framework.springsecurity.bean.MyRole;

/**
 * 自定义权限信息
 * @author Administrator
 *
 */
public interface MyRoleDaoInf {
	
	/**
	 * 加载所有的权限信息
	 * @return
	 * @throws Exception
	 */
	public List<MyRole> queryRoles()throws Exception;
	
	

	
	/**
	 * 用据用户id加载所有的用户权限信息
	 * @param id 当前的用户id
	 * @return 当前的用户权限信息
	 * @throws Exception
	 */
	public List<MyRole> queryUserRoleByUsId(int id)throws Exception;
}
