package com.liujun.framework.springsecurity.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liujun.framework.springsecurity.bean.MyResource;
import com.liujun.framework.springsecurity.bean.MyRole;
import com.liujun.framework.springsecurity.dao.MyResourceDaoInf;
import com.liujun.framework.springsecurity.service.MyResourceServiceInf;

/**
 * 进行用户资源加载操作
 * 
 * @author liujun
 * 
 * @date 2014年12月22日
 * @vsersion 0.0.1
 */
@Service
public class MyResourceServiceImpl implements MyResourceServiceInf {

	/**
	 * 日志操作
	 */
	private Logger log = Logger.getLogger(MyResourceServiceImpl.class);

	/**
	 * 资源数据库操作
	 */
	@Autowired
	private MyResourceDaoInf myResourceDao;

	@Override
	public List<MyResource> queryResources() {
		List<MyResource> list = null;

		try {
			list = myResourceDao.queryResources();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("MyResourceServiceImpl queryResources error:", e);
		}

		return list;
	}

	@Override
	public List<MyResource> queryResourceByRoleId(int roldId) {
		List<MyResource> list = null;

		try {
			list = myResourceDao.queryResourceByRoleId(roldId);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("MyResourceServiceImpl queryResourceByRoleId error:", e);
		}

		return list;
	}

	@Override
	public MyRole queryRoleResouce(MyRole role) {

		MyRole result = role;

		if (null != result) {
			result.setResourceS(this.queryResourceByRoleId(role.getId()));
		}

		return result;
	}

}
