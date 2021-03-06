package com.liujun.mobile.swindle.service.rule.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.liujun.mobile.swindle.bean.GuestItem;
import com.liujun.mobile.swindle.bean.MainItem;
import com.liujun.mobile.swindle.bean.MatchKey;
import com.liujun.mobile.swindle.console.PatternRuleException;
import com.liujun.mobile.swindle.console.RoleMatchSign;
import com.liujun.mobile.swindle.service.rule.MatchRuleServiceInf;

/**
 * 针对主谓宾结构的数据进行解析
 * 
 * 例如:{是}#{10}{先生|小姐|太太|女士|妈妈|爸爸|爷爷|奶奶}#{10}{兴趣|需要|了解|知道|清楚|想法|投资|买房|买楼|换房}
 * 
 * @author liujun
 * @date 2016年3月5日
 * @verion 0.0.1
 */
public class MatchRuleMainCallGuestServiceImpl implements MatchRuleServiceInf
{

	/**
	 * 进行主谓宾结构的规则验证
	 */
	private static final Pattern PATTERN_MAINCALLGUEST = Pattern.compile("^\\{[\\S]+\\}\\#\\(\\d+\\)\\{[\\S]+\\}\\#\\(\\d+\\)\\{[\\S]+\\}$");

	/**
	 * 对主谓宾结构进行解释得到语法的规则
	 * 
	 * @param pattern
	 *            规则信息
	 * @return 解析后的数据
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

		// 检查是否存在开始括号标签
		int callstartindex = info.indexOf(RoleMatchSign.MATCH_SIGN_START.getSign());
		int callendindex = info.indexOf(RoleMatchSign.MATCH_SIGN_END.getSign()) + 1;

		// 谓语字符串
		String callStr = info.substring(callstartindex, callendindex);
		info = info.substring(callendindex);

		// 获取谓语结构与宾语结构中内容占位符长度
		int cgmatchNumIndex = info.indexOf(RoleMatchSign.MATCH_SIGN_CON_NUM_START.getSign()) + 2;
		int cgmatchNumEndindex = info.indexOf(RoleMatchSign.MATCH_SIGN_CON_NUM_END.getSign());

		String cgmatchNum = info.substring(cgmatchNumIndex, cgmatchNumEndindex);

		info = info.substring(cgmatchNumEndindex + 1);

		// 解析主语结构
		matchKey.setMainkey(this.getMainInfo(main));
		// 解析占位符内容长度数据
		matchKey.setCallExp(RoleMatchSign.MATCH_SIGN_CON_SQL.getSign() + matchNum + RoleMatchSign.MATCH_SIGN_END.getSign());
		// 解析谓语结构
		matchKey.setCallKey(this.getMainInfo(callStr));
		// 解析占位符数据
		matchKey.setContExp(RoleMatchSign.MATCH_SIGN_CON_SQL.getSign() + cgmatchNum + RoleMatchSign.MATCH_SIGN_END.getSign());

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
		if (pattarrs != null)
		{
			// 其余信息分为左右两块，左边为需匹配的关键字，右边为排除的关键字
			// 左边的需匹配的关键字
			String[] matchkeys = pattarrs[0].split(RoleMatchSign.MATCH_SIGN_ITEM_SPIT.getSign());

			// 匹配关键字
			if (matchkeys != null)
			{
				for (String matchkey : matchkeys)
				{
					item.addMatchKey(matchkey);
				}
			}

			// 检查是否存在排除关键字
			if (pattarrs.length > 1)
			{
				// 右边排除的匹配的关键字
				String[] notmatchkeys = pattarrs[1].split(RoleMatchSign.MATCH_SIGN_ITEM_SPIT.getSign());

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
		MatchRuleMainCallGuestServiceImpl patt = new MatchRuleMainCallGuestServiceImpl();
		try
		{
			String value = "{是}#(10){先生|小姐|太太|女士|妈妈|爸爸|爷爷|奶奶}#(10){兴趣|需要|了解|知道|清楚|想法|炒股|贷款|股票|执仓|基金|期货|石油|原油|白银|外汇|美元|发票|投资|买房|买楼|换房}";

			MatchKey match = patt.explain(value);
			//
			System.out.println(match);

			// String matSql = patt.getSql(match);
			//
			// System.out.println(matSql);

			Matcher mat = Pattern.compile("^\\{[\\S]+\\}\\#\\(\\d+\\)\\{[\\S]+\\}\\#\\(\\d+\\)\\{[\\S]+\\}$").matcher(value);

			System.out.println(mat.matches());

		} catch (PatternRuleException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkRuleMatch(String data)
	{
		if (null != data)
		{
			Matcher matRule = PATTERN_MAINCALLGUEST.matcher(data);

			return matRule.matches();
		}

		return false;
	}

}
