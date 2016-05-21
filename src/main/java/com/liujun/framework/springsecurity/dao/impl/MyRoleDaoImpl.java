package com.liujun.framework.springsecurity.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.liujun.framework.springsecurity.bean.MyRole;
import com.liujun.framework.springsecurity.dao.MyRoleDaoInf;
import com.liujun.framework.springsecurity.dao.impl.base.BaseJdbcDaoSupper;

/**
 * 当前所有权限信息
 * 
 * @author Administrator
 * 
 */
@Repository
public class MyRoleDaoImpl extends BaseJdbcDaoSupper implements MyRoleDaoInf {

	/**
	 * 用户角色信息
	 */
	private Logger log = Logger.getLogger(MyRoleDaoImpl.class);

	@Override
	public List<MyRole> queryRoles() throws Exception {

		List<MyRole> list = this.getJdbctempl().query("select * from role",
				new Object[0], new getRowValue());
		
		log.info("MyRoleDaoImpl queryRoles result:" + list);

		return list;
	}
	
	private class getRowValue implements RowMapper<MyRole>{

		@Override
		public MyRole mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			MyRole myro = new MyRole();
			myro.setId(rs.getInt("id"));
			myro.setRoleName(rs.getString("name"));
			myro.setDescn(rs.getString("descn"));
			return myro;
		}

	}

	@Override
	public List<MyRole> queryUserRoleByUsId(int id) throws Exception {
		List<MyRole> list = this.getJdbctempl().query("select ro.id,ro.name,ro.descn from user_role ur left join role ro  on ur.role_id = ro.id where  ur.user_id=?",
				new Object[]{id}, new getRowValue());
		
		log.info("MyRoleDaoImpl queryRoles result:" + list);
		return list;
	}
}
