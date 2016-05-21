package com.som.user.userjdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.som.user.userjdbc.util.PropertiesUtils;

public abstract class JdbcBase<T>
{
	/**
	 * 通过默认的配制得到数据库连接
	 * 
	 * @return
	 */
	public Connection getConn()
	{
		try
		{
			Class.forName(PropertiesUtils.getInstance().getValue("driver.classname"));

			Connection conn = DriverManager.getConnection(PropertiesUtils.getInstance().getValue("database.url"), PropertiesUtils.getInstance().getValue("database.username"),
					PropertiesUtils.getInstance().getValue("database.password"));

			return conn;

		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 进行数据更新操作
	 * 
	 * @param sql
	 * @param param
	 * @return
	 */
	public int upd(String sql, LinkedHashMap<String, Object> param)
	{
		Connection conn = this.getConn();

		if (null != conn)
		{
			PreparedStatement state = null;
			try
			{
				state = conn.prepareStatement(sql);

				this.setParam(param, state);

				return state.executeUpdate();

			} catch (SQLException e)
			{
				e.printStackTrace();
			} finally
			{
				this.close(state);
				this.close(conn);
			}
		}

		return -1;

	}

	/**
	 * 设置参数信息
	 * 
	 * @param param
	 * @param state
	 * @throws SQLException
	 */
	private void setParam(LinkedHashMap<String, Object> param, PreparedStatement state) throws SQLException
	{
		if (null != param && !param.isEmpty())
		{
			Entry<String, Object> entry = null;

			int index = 1;

			Iterator<Entry<String, Object>> hashEntry = param.entrySet().iterator();

			while (hashEntry.hasNext())
			{
				entry = hashEntry.next();
				// 如果当前为短int类型
				if (entry.getValue() instanceof Integer)
				{
					state.setInt(index, (Integer) entry.getValue());
				}
				// 如果为long类型
				else if (entry.getValue() instanceof Long)
				{
					state.setLong(index, (Long) entry.getValue());
				}
				// 如果当前为字符串
				else if (entry.getValue() instanceof String)
				{
					state.setString(index, String.valueOf(entry.getValue()));
				}

				index++;
			}

		}
	}

	/**
	 * 查询方法
	 * 
	 * @param sql
	 *            SQL
	 * @param param
	 *            参数
	 * @return
	 */
	public List<T> queryForList(String sql, LinkedHashMap<String, Object> param, ParseBeanInf<T> parse)
	{
		Connection conn = this.getConn();

		if (null != conn)
		{
			List<T> list = null;

			PreparedStatement state = null;
			try
			{
				state = conn.prepareStatement(sql);

				this.setParam(param, state);

				ResultSet rs = state.executeQuery();

				list = new LinkedList<T>();

				while (rs.next())
				{
					list.add(parse.parse(rs));
				}

				return list;
			} catch (SQLException e)
			{
				e.printStackTrace();
			} finally
			{
				this.close(state);
				this.close(conn);
			}
		}

		return null;
	}

	/**
	 * 查询方法
	 * 
	 * @param sql
	 *            SQL
	 * @param param
	 *            参数
	 * @return
	 */
	public T queryForOne(String sql, LinkedHashMap<String, Object> param, ParseBeanInf<T> parse)
	{
		Connection conn = this.getConn();

		if (null != conn)
		{
			T result = null;

			PreparedStatement state = null;
			try
			{
				state = conn.prepareStatement(sql);

				this.setParam(param, state);

				ResultSet rs = state.executeQuery();

				if (rs.next())
				{
					result = parse.parse(rs);
				}

				return result;
			} catch (SQLException e)
			{
				e.printStackTrace();
			} finally
			{
				this.close(state);
				this.close(conn);
			}
		}

		return null;
	}

	/**
	 * 关闭操作
	 * 
	 * @param close
	 */
	public void close(AutoCloseable close)
	{
		if (null != close)
		{
			try
			{
				close.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
