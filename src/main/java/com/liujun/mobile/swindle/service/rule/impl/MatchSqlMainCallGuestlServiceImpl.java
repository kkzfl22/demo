package com.liujun.mobile.swindle.service.rule.impl;

import java.util.LinkedList;
import java.util.List;

import com.liujun.mobile.swindle.bean.GuestItem;
import com.liujun.mobile.swindle.bean.MatchKey;
import com.liujun.mobile.swindle.console.RoleMatchSign;
import com.liujun.mobile.swindle.service.rule.MatchSqlServiceInf;

/**
 * 用来进行主谓宾结构的数据生成SQL语句,不带排除语句
 * 
 * @author liujun
 * @date 2016年3月5日
 * @verion 0.0.1
 */
public class MatchSqlMainCallGuestlServiceImpl implements MatchSqlServiceInf
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

			// 主语与谓语之间的占位符
			String callExp = bean.getCallExp();

			// 谓语与宾语之间的占位符
			String guestExp = bean.getContExp();

			// 得到主语的需匹配的信息
			LinkedList<String> matchList = bean.getMainkey().getMatchKey();

			// 得到谓语中的需要匹配的信息
			LinkedList<String> callList = bean.getCallKey().getMatchKey();

			// 得到宾语信息
			List<GuestItem> guestList = bean.getGuestkey();

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

					String statementsql = STATEMENTSQL;
					String matchsql = MATCHSQL;

					statementsql = statementsql.replaceAll("#\\{guest_key\\}", guest.getKey());
					sql.append(" ( ").append(statementsql);

					String matchSqlinfo = "";

					for (int k = 0; k < callList.size(); k++)
					{
						// 主语中关键字需要匹配的
						for (int j = 0; j < matchList.size(); j++)
						{
							matchSqlinfo += matchList.get(j) + callExp + callList.get(k) + guestExp + guest.getKey() + RoleMatchSign.MATCH_SIGN_ITEM_SPIT.getSign();
						}

						if (k == callList.size() - 1)
						{
							matchSqlinfo = matchSqlinfo.substring(0,matchSqlinfo.length()-2);
						}
					}

					matchsql = matchsql.replaceAll("#\\{match_info\\}", matchSqlinfo);
					sql.append(matchsql);

					// 检查宾语中是否存在排除关键字

					sql.append(")");

					sql.append("\r\n");
				}
			}

			result = result.replaceAll("#\\{UNION_ALL_SQL\\}", sql.toString());

		}

		return result;
	}
}
