package com.liujun.framework.springsecurity.dao;

import java.util.List;

import com.liujun.framework.springsecurity.bean.MyResource;

/**
 * 进行资源的数据库加载操作
 * @author Administrator
 *
 */
public interface MyResourceDaoInf {
	
	/**
	 * 查询出所有的资源信息
	 * @return 结果信息
	 * @throws Exception
	 */
	public List<MyResource> queryResources() throws Exception;
	
	/**
	 * 根据roleid来查询出当前权限的资源
	 * @param roldId
	 * @return
	 * @throws Exception
	 */
	public List<MyResource> queryResourceByRoleId(int roldId)throws Exception;

}
