package com.liujun.framework.springsecurity.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.liujun.framework.springsecurity.bean.MyResource;
import com.liujun.framework.springsecurity.bean.MyRole;

/**
 * 得到资源的信息
 * @author liujun
 *
 * @date 2014年12月22日
 * @vsersion 0.0.1
 */
public interface MyResourceServiceInf {
	

	
	/**
	 * 查询出所有的资源信息
	 * @return 结果信息
	 * @throws DataAccessException
	 */
	public List<MyResource> queryResources() ;
	
	/**
	 * 根据roleid来查询出当前权限的资源
	 * @param roldId
	 * @return
	 * @throws DataAccessException
	 */
	public List<MyResource> queryResourceByRoleId(int roldId);
	
	/**
	 * 查询用户的权限的资源信息
	 * @param role 授权信息
	 * @return 加载资源的权限信息
	 */
	public MyRole queryRoleResouce(MyRole role);



}
