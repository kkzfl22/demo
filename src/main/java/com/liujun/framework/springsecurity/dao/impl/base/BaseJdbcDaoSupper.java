package com.liujun.framework.springsecurity.dao.impl.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public  class BaseJdbcDaoSupper {

	/**
	 * 进行
	 */
	@Autowired
	private JdbcTemplate jdbctempl;

	/**
	 * 进行通用的更新操作
	 * 
	 * @param sql
	 *            sql语句
	 * @param param
	 *            参数信息
	 * @return 更新的结果
	 * @throws DataAccessException
	 */
	public int updProcess(String sql, Object... param)
			throws DataAccessException {
		return this.jdbctempl.update(sql, param);
	}

	/**
	 * 查询数据返回一个通用结果集
	 * 
	 * @param sql
	 *            结果信息
	 * @param param
	 *            参数信息
	 * @return 结果集信息
	 */
	public List<Map<String, Object>> queryForListProcess(String sql,
			Object... param) throws DataAccessException {
		return this.jdbctempl.queryForList(sql, param);
	}

	/**
	 * 查询数所返回一个通用的结果信息
	 * 
	 * @param sql
	 *            结果集对象信息
	 * @param param
	 *            参数 信息
	 * @return 结果信息
	 * @throws DataAccessException
	 */
	public Map<String, Object> queryMapProcess(String sql, Object... param)
			throws DataAccessException {
		return this.jdbctempl.queryForMap(sql, param);
	}

	/**
	 * 查询数据返回一个long的结果
	 * 
	 * @param sql
	 *            sql信息
	 * @param param
	 *            参数信息
	 * @return 返回一个long 的结果信
	 * @throws DataAccessException
	 */
	public long queryForLong(String sql, Object... param)
			throws DataAccessException {
		return this.jdbctempl.queryForLong(sql, param);
	}
	

	/**
	 * 查询数据返回一个int的结果
	 * 
	 * @param sql
	 *            sql数据
	 * @param param
	 *            参数
	 * @return int的结果
	 * @throws DataAccessException
	 */
	public int queryForInt(String sql, Object... param)
			throws DataAccessException {
		return this.jdbctempl.queryForInt(sql, param);
	}
	
	

	public JdbcTemplate getJdbctempl() {
		return jdbctempl;
	}

}
