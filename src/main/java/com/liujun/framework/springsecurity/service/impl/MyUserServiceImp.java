package com.liujun.framework.springsecurity.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liujun.framework.springsecurity.bean.MyUser;
import com.liujun.framework.springsecurity.dao.MyUserDaoInf;
import com.liujun.framework.springsecurity.service.MyUserServiceInf;

@Service
public class MyUserServiceImp implements MyUserServiceInf {

	/**
	 * 日志操作
	 */
	private Logger log = Logger.getLogger(MyUserServiceImp.class);

	/**
	 * 用户dao
	 */
	@Autowired
	private MyUserDaoInf myuserDao;

	@Override
	public MyUser queryUserByLogin(MyUser user) {

		MyUser userRs = null;

		try {
			userRs = myuserDao.queryUserByLogin(user);
		} catch (Exception e) {
			log.error("MyUserServiceImp queryUserByLogin DataAccessException:",
					e);
		}

		return userRs;
	}

	@Override
	public MyUser queryUserByUserName(String name) {
		MyUser userRs = null;

		try {
			userRs = myuserDao.queryUserByUserName(name);
		} catch (Exception e) {
			log.error("MyUserServiceImp queryUserByUserName DataAccessException:",
					e);
		}

		return userRs;
	}

}
