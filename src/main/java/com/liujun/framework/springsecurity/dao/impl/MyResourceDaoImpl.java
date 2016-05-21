package com.liujun.framework.springsecurity.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.liujun.framework.springsecurity.bean.MyResource;
import com.liujun.framework.springsecurity.dao.MyResourceDaoInf;
import com.liujun.framework.springsecurity.dao.impl.base.BaseJdbcDaoSupper;

/**
 * 数据库资源操作
 * 
 * @author Administrator
 * 
 */
@Repository
public class MyResourceDaoImpl extends BaseJdbcDaoSupper implements MyResourceDaoInf {

	/**
	 * 日志信息
	 */
	private Logger log = Logger.getLogger(MyResourceDaoImpl.class);

	@Override
	public List<MyResource> queryResources() throws Exception {

		List<MyResource> list = this.getJdbctempl().query("select * from resc ", new Object[0], new rowResource());

		log.info("MyResourceDaoImpl queryResources result :" + list);

		return list;
	}

	private class rowResource implements RowMapper<MyResource> {

		@Override
		public MyResource mapRow(ResultSet rs, int rowNum) throws SQLException {
			MyResource res = new MyResource();

			res.setId(rs.getInt("id"));
			res.setName(rs.getString("name"));
			res.setResType(rs.getString("res_type"));
			res.setResString(rs.getString("res_string"));
			res.setPriority(rs.getInt("priority"));
			res.setDescn(rs.getString("descn"));

			return res;
		}
	}

	@Override
	public List<MyResource> queryResourceByRoleId(int roldId) throws Exception {
		List<MyResource> list = this.getJdbctempl().query("select re.id,re.name,re.res_type,re.res_string,re.priority,re.descn from resc_role rer left join resc re on rer.resc_id = re.id where rer.role_id=?", 
				new Object[]{roldId}, new rowResource());

		log.info("MyResourceDaoImpl queryResourceByRoleId result :" + list);
		
		return list;
	}

}
