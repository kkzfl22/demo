package com.liujun.mobile.swindle.service.rule.impl;

import java.util.LinkedList;
import java.util.List;

import com.liujun.mobile.swindle.bean.GuestItem;
import com.liujun.mobile.swindle.bean.MatchKey;
import com.liujun.mobile.swindle.console.RoleMatchSign;
import com.liujun.mobile.swindle.service.rule.MatchSqlServiceInf;

/**
 * 用来进行主宾结构的数据生成SQL语句
 * 
 * @author liujun
 * @date 2016年3月5日
 * @verion 0.0.1
 */
public class MatchSqlMainGuestlServiceImpl implements MatchSqlServiceInf
{
	/**
	 * 最终执行的SQL语句
	 */
	private final String EXECSQL = "SELECT  /*+PARALLEL 16*/ A.F,A.D,B.* FROM cjh03 B,(SELECT '角色' F,ID,WMSYS.WM_CONCAT(D) D FROM ( #{UNION_ALL_SQL} )GROUP BY ID)A WHERE A.ID = B.ID; ";

	/**
	 * 标准的查询前半部分的SQL
	 */
	private final String STATEMENTSQL = " SELECT /*+PARALLEL 16*/ '#{guest_key}' D,T.* FROM (select id,substr(text,1,30) text from cjh03) T WHERE ";

	/**
	 * 匹配的sql信息
	 */
	private final String MATCHSQL = " REGEXP_LIKE (TEXT,'#{match_info}') ";

	/**
	 * 排除的SQL语句
	 */
	private final String EXECUDESQL = " AND NOT  REGEXP_LIKE (TEXT,'#{execude_info}') ";

	/**
	 * 添加union连接语句
	 */
	private final String UNIONSQL = " UNION ALL ";

	/**
	 * 生成SQL信息
	 * 
	 * @param bean
	 * @return
	 */
	public String getSql(MatchKey bean)
	{
		String result = "";

		if (null != bean)
		{
			result = EXECSQL;

			StringBuilder sql = new StringBuilder();

			String con = bean.getContExp();

			// 得到宾语信息
			List<GuestItem> guestList = bean.getGuestkey();

			// 得到主语的需匹配的信息
			LinkedList<String> matchList = bean.getMainkey().getMatchKey();

			// 得到主语中需排除的信息
			LinkedList<String> excludelist = bean.getMainkey().getNotMatchKey();

			// 按照宾语结构进行遍历
			if (null != guestList && !guestList.isEmpty())
			{
				GuestItem guest = null;

				for (int i = 0; i < guestList.size(); i++)
				{
					// 如果当前不是第一次 需添加union all语句
					if (i > 0)
					{
						sql.append(UNIONSQL);
					}

					guest = guestList.get(i);

					// 排除的宾语关键字
					LinkedList<String> guestExecudeList = guest.getExclude();

					String statementsql = STATEMENTSQL;
					String matchsql = MATCHSQL;
					String execudesql = EXECUDESQL;

					statementsql = statementsql.replaceAll("#\\{guest_key\\}", guest.getKey());
					sql.append(" ( ").append(statementsql);

					String matchSqlinfo = "";
					String execudeSqlinfo = "";
					// 主语中关键字需要匹配的
					for (int j = 0; j < matchList.size(); j++)
					{
						if (j == matchList.size() - 1)
						{
							matchSqlinfo += matchList.get(j) + con + guest.getKey();
						} else
						{
							matchSqlinfo += matchList.get(j) + con + guest.getKey() + RoleMatchSign.MATCH_SIGN_ITEM_SPIT.getSign();
						}
					}

					matchsql = matchsql.replaceAll("#\\{match_info\\}", matchSqlinfo);
					sql.append(matchsql);

					// 主语中需要排除的
					for (int j = 0; j < excludelist.size(); j++)
					{
						if (j == excludelist.size() - 1)
						{
							execudeSqlinfo += excludelist.get(j) + con + guest.getKey();
						} else
						{
							execudeSqlinfo += excludelist.get(j) + con + guest.getKey() + RoleMatchSign.MATCH_SIGN_ITEM_SPIT.getSign();
						}
					}

					if (!excludelist.isEmpty())
					{
						execudesql = execudesql.replaceAll("#\\{execude_info\\}", execudeSqlinfo);
						sql.append(execudesql);
					}
					
					// 检查宾语中是否存在排除关键字

					if (null != guestExecudeList && !guestExecudeList.isEmpty())
					{
						// 进行宾语的排除关键字
						for (int k = 0; k < guestExecudeList.size(); k++)
						{

							String guestMainExecudesql = EXECUDESQL;
							String execuleMainSqlinfo = "";

							// 主语中关键字与排除宾语中的结合需要排除
							for (int j = 0; j < matchList.size(); j++)
							{
								if (j == matchList.size() - 1)
								{
									execuleMainSqlinfo += matchList.get(j) + con + guestExecudeList.get(k);
								} else
								{
									execuleMainSqlinfo += matchList.get(j) + con + guestExecudeList.get(k) + RoleMatchSign.MATCH_SIGN_ITEM_SPIT.getSign();
								}
							}

							guestMainExecudesql = guestMainExecudesql.replaceAll("#\\{execude_info\\}", execuleMainSqlinfo);
							sql.append(guestMainExecudesql);

							String guestExecudesql = EXECUDESQL;
							String execuleSqlinfo = "";

							// 主语中排除的关键字与排除的宾语也需要排除
							for (int j = 0; j < excludelist.size(); j++)
							{
								if (j == excludelist.size() - 1)
								{
									execuleSqlinfo += excludelist.get(j) + con + guestExecudeList.get(k);
								} else
								{
									execuleSqlinfo += excludelist.get(j) + con + guestExecudeList.get(k) + RoleMatchSign.MATCH_SIGN_ITEM_SPIT.getSign();
								}
							}

							guestExecudesql = guestExecudesql.replaceAll("#\\{execude_info\\}", execuleSqlinfo);
							sql.append(guestExecudesql);
						}
					}

					sql.append(")");

					sql.append("\r\n");
				}
			}

			result = result.replaceAll("#\\{UNION_ALL_SQL\\}", sql.toString());

		}

		return result;
	}
}
