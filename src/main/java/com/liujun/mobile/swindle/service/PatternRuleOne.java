package com.liujun.mobile.swindle.service;

import java.util.LinkedList;
import java.util.List;

import com.liujun.mobile.swindle.bean.GuestItem;
import com.liujun.mobile.swindle.bean.MainItem;
import com.liujun.mobile.swindle.bean.MatchKey;
import com.liujun.mobile.swindle.console.PatternRuleException;
import com.liujun.mobile.swindle.console.RoleMatchSign;

public class PatternRuleOne
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
	private final String EXECUDESQL = " and not  REGEXP_like (TEXT,'#{execude_info}') ";

	/**
	 * 添加union连接语句
	 */
	private final String UNIONSQL = " union all ";

	/**
	 * 规则1
	 */
	private static final String patternone = "{我|这|是&&!不是|在|去|来}#(10){学校|分校|(局&&!大局,局部)|(科&&!科幻,科举,科考,科目)}";

	/**
	 * 进行解释得到语法的规则
	 * 
	 * @param pattern
	 *            规则信息
	 * @return 1，主，2，谓，3宾 对应的就是关键字，与排除关键字
	 */
	public MatchKey explain(String pattern) throws PatternRuleException
	{
		MatchKey matchKey = new MatchKey();

		String info = pattern;

		// 检查是否存在开始括号标签
		int startindex = info.indexOf(RoleMatchSign.MATCH_SIGN_START.getSign());
		int endindex = info.indexOf(RoleMatchSign.MATCH_SIGN_END.getSign()) + 1;

		// 主语字符串
		String main = info.substring(startindex, endindex);

		info = info.substring(endindex);

		// 获取中间匹配的字符串
		int matchNumIndex = info.indexOf(RoleMatchSign.MATCH_SIGN_CON_NUM_START.getSign()) + 2;
		int matchNumEndindex = info.indexOf(RoleMatchSign.MATCH_SIGN_CON_NUM_END.getSign());

		String matchNum = info.substring(matchNumIndex, matchNumEndindex);

		info = info.substring(matchNumEndindex + 1);

		// 解析主语结构
		matchKey.setMainkey(this.getMainInfo(main));
		// 解析内容长度数据
		matchKey.setContExp(RoleMatchSign.MATCH_SIGN_CON_SQL.getSign() + matchNum + RoleMatchSign.MATCH_SIGN_END.getSign());
		// 解析宾语结构数据
		matchKey.setGuestkey(this.getGuestInfo(info));

		return matchKey;

	}

	/**
	 * 针对主进行解析 例如：{[我],[这],[是]&&![不是]}
	 * 
	 * @param pattern
	 *            单个表达式信息
	 * @return 解析后的关键字与排除关键字信息
	 */
	private MainItem getMainInfo(String pattern) throws PatternRuleException
	{
		MainItem item = new MainItem();

		// 1,去掉首尾的括号
		String patt = pattern.substring(1, pattern.length() - 1);

		// 2，将关键与排除关键字识别
		String[] pattarrs = patt.split(RoleMatchSign.MATCH_SIGN_NOTORIN_MAT.getSign());

		// 首先进行主关键字提取
		if (pattarrs != null && pattarrs.length == 2)
		{
			// 其余信息分为左右两块，左边为需匹配的关键字，右边为排除的关键字
			// 左边的需匹配的关键字
			String[] matchkeys = pattarrs[0].split(RoleMatchSign.MATCH_SIGN_ITEM_SPIT.getSign());

			// 右边排除的匹配的关键字
			String[] notmatchkeys = pattarrs[1].split(RoleMatchSign.MATCH_SIGN_ITEM_SPIT.getSign());

			// 匹配关键字
			if (matchkeys != null)
			{
				for (String matchkey : matchkeys)
				{
					item.addMatchKey(matchkey);
				}
			}
			// 排除关键字
			if (notmatchkeys != null)
			{
				for (int i = 0; i < notmatchkeys.length; i++)
				{
					if (i > 0)
					{
						item.addNotMatchKey(notmatchkeys[i]);
					} else
					{
						item.addNotMatchKey(notmatchkeys[i].substring(1));
					}
				}
			}
		} else
		{
			throw new PatternRuleException("the item pattern is error!");
		}

		return item;
	}

	/**
	 * 得到宾语结构数据信息
	 * 
	 * 例{学校|分校|(局&&!大局,局部)|(科&&,科幻,科举,科考,科目)}
	 * 
	 * @param context
	 * @return
	 */
	private List<GuestItem> getGuestInfo(String guestStr)
	{
		List<GuestItem> list = new LinkedList<GuestItem>();

		// 1,去掉首尾的括号
		String patt = guestStr.substring(1, guestStr.length() - 1);

		// 2，将关键与排除关键字识别
		String[] pattGuests = patt.split(RoleMatchSign.MATCH_SIGN_ITEM_SPIT.getSign());

		if (null != pattGuests && pattGuests.length > 0)
		{
			GuestItem guestInfo = null;
			for (String guest : pattGuests)
			{
				guestInfo = new GuestItem();
				// 检查是否有排除信息,如果不存在，则直接放到关键字中
				if (guest.indexOf(RoleMatchSign.MATCH_SIGN_NOTORIN_MAT.getSign()) == -1)
				{
					guestInfo.setKey(guest);
				} else
				{
					String[] guestArrays = guest.split(RoleMatchSign.MATCH_SIGN_NOTORIN_MAT.getSign());
					// 下标为0的是宾语信息
					guestInfo.setKey(guestArrays[0].substring(1));
					// 下标为1的是排除宾语信息
					String[] notKey = (guestArrays[1].substring(1, guestArrays[1].length() - 1)).split(RoleMatchSign.MATCH_SIGN_EXCLUDE_SPIT.getSign());

					// 存储排除信息
					LinkedList<String> excludeList = new LinkedList<String>();

					for (String notkeyInfo : notKey)
					{
						excludeList.add(notkeyInfo);
					}

					guestInfo.setExclude(excludeList);
				}

				list.add(guestInfo);
			}
		}

		return list;
	}

	public static void main(String[] args)
	{
		PatternRuleOne patt = new PatternRuleOne();
		try
		{
			MatchKey match = patt.explain(patternone);

			System.out.println(match);

			String matSql = patt.getSql(match);

			System.out.println(matSql);

		} catch (PatternRuleException e)
		{
			e.printStackTrace();
		}
	}

	public String getSql(MatchKey bean)
	{
		String result = "";

		if (null != bean)
		{
			result = EXECSQL;

			StringBuilder sql = new StringBuilder();

			MainItem mainRule = bean.getMainkey();

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
					sql.append(RoleMatchSign.MATCH_SIGN_SQL_MATCH_START.getSign()).append(statementsql);

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

					execudesql = execudesql.replaceAll("#\\{execude_info\\}", execudeSqlinfo);
					sql.append(execudesql);

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

					sql.append(RoleMatchSign.MATCH_SIGN_SQL_MATCH_END.getSign());

					sql.append("\r\n");
				}
			}

			result = result.replaceAll("#\\{UNION_ALL_SQL\\}", sql.toString());

		}

		return result;
	}

}
