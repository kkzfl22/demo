package com.liujun.framework.springsecurity.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.liujun.framework.springsecurity.bean.MyRole;
import com.liujun.framework.springsecurity.bean.MyUser;
import com.liujun.framework.springsecurity.dao.MyRoleDaoInf;
import com.liujun.framework.springsecurity.service.MyRoleServiceInf;

/**
 * 用户权限信息
 * @author liujun
 *
 * @date 2014年12月22日
 * @vsersion 0.0.1
 */
@Service
public class MyRoleServiceImpl implements MyRoleServiceInf {

	/**
	 * 日志操作
	 */
	private Logger log = Logger.getLogger(MyUserServiceImp.class);

	/**
	 * 权限信息
	 */
	@Autowired
	private MyRoleDaoInf myRoleDao;

	@Override
	public List<MyRole> queryRoles() {
		List<MyRole> userRoles = null;
		try {
			userRoles = myRoleDao.queryRoles();
		} catch (Exception e) {
			log.error("MyUserServiceImp queryUserByLogin DataAccessException:", e);
		}

		return userRoles;
	}

	/**
	 * 用据用户id加载所有的用户权限信息
	 * 
	 * @param id
	 *            当前的用户id
	 * @return 当前的用户权限信息
	 * @throws DataAccessException
	 */
	public List<MyRole> queryUserRoleByUsId(int id) {
		List<MyRole> userRoles = null;
		try {
			userRoles = myRoleDao.queryUserRoleByUsId(id);
		} catch (Exception e) {
			log.error("MyUserServiceImp queryUserByLogin DataAccessException:", e);
		}

		return userRoles;
	}

	@Override
	public MyUser getRoleByUserId(MyUser user) {

		MyUser result = user;
		
		if(null != result)
		{
			//加载用户的权限信息
			result.setUserRoles(this.queryUserRoleByUsId(user.getId()));
		}

		return result;
	}

}
