package com.som.user.userjdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 进行数据转换
 * 
 * @author liujun
 * @date 2016年4月19日
 * @verion 0.0.1
 * @param <T>
 */
public interface ParseBeanInf<T>
{
	/**
	 * 转换数据
	 * 
	 * @param rs
	 * @return
	 */
	public T parse(ResultSet rs) throws SQLException;
}
