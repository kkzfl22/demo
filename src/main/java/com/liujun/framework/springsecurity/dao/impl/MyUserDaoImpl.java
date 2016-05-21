package com.liujun.framework.springsecurity.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.liujun.framework.springsecurity.bean.MyUser;
import com.liujun.framework.springsecurity.dao.MyUserDaoInf;
import com.liujun.framework.springsecurity.dao.impl.base.BaseJdbcDaoSupper;

/**
 * 用户信息数据库访问实现
 * 
 * @author Administrator
 * 
 */
@Repository
public class MyUserDaoImpl extends BaseJdbcDaoSupper implements MyUserDaoInf {

	/**
	 * 日志
	 */
	private Logger log = Logger.getLogger(MyUserDaoImpl.class);

	@Override
	public List<MyUser> queryUserList(int id) throws Exception {
		log.info("UserDaoImpl queryUserList param:id=" + id);

		String sql = "select * from user us where us.id = ?";

		List<MyUser> list = this.getJdbctempl().query(sql, new Object[]{id},
				new RowValue());

		log.info("UserDaoImpl queryUserList result :" + list);

		return list;
	}

	private class RowValue implements RowMapper<MyUser> {

		@Override
		public MyUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			MyUser us = new MyUser();
			us.setId(rs.getInt("id"));
			us.setName(rs.getString("username"));
			us.setPassword(rs.getString("password"));
			us.setStatus(rs.getInt("status"));
			us.setDescn(rs.getString("descn"));

			return us;
		}
	}

	@Override
	public int insertUser(MyUser user) throws Exception {
		return 0;
	}

	@Override
	public MyUser queryUserByLogin(MyUser user) throws Exception {
		log.info("UserDaoImpl queryUserByLogin param:username=" + user.getName()
				+ ";pwd=" + user.getPassword());

		String sql = "select * from user us where us.username = ? and us.password = ?";

		List<MyUser> list = this.getJdbctempl().query(sql,
				new Object[]{user.getName(), user.getPassword()},
				new RowValue());

		log.info("UserDaoImpl queryUserByLogin result :" + list);

		if (null != list && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public MyUser queryUserByUserName(String name) throws Exception {
		log.info("UserDaoImpl queryUserByLogin param:username=" + name);

		String sql = "select * from user us where us.username = ? ";

		List<MyUser> list = this.getJdbctempl().query(sql,
				new Object[]{name},
				new RowValue());

		log.info("UserDaoImpl queryUserByLogin result :" + list);

		if (null != list && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}


}
