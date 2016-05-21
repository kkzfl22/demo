package com.liujun.mobile.swindle.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liujun.mobile.swindle.bean.MatchKey;
import com.liujun.mobile.swindle.bean.RuleCsvBean;
import com.liujun.mobile.swindle.console.PatternRuleException;
import com.liujun.mobile.swindle.console.RoleMatchSystemParam;
import com.liujun.mobile.swindle.service.csv.DataOperatorInf;
import com.liujun.mobile.swindle.service.csv.ParseCsvDataLineInf;
import com.liujun.mobile.swindle.service.csv.impl.DataCsvOperatorImpl;
import com.liujun.mobile.swindle.service.rule.MatchRuleServiceInf;
import com.liujun.mobile.swindle.service.rule.MatchSqlServiceInf;
import com.liujun.mobile.swindle.service.rule.impl.MatchRuleMainCallGuestServiceImpl;
import com.liujun.mobile.swindle.service.rule.impl.MatchRuleMainGuestServiceImpl;
import com.liujun.mobile.swindle.service.rule.impl.MatchSqlMainCallGuestlServiceImpl;
import com.liujun.mobile.swindle.service.rule.impl.MatchSqlMainGuestlServiceImpl;

/**
 * 进行csv导入并解析数据
 * 
 * @author liujun
 * @date 2016年3月5日
 * @verion 0.0.1
 */
public class CsvInputAnalysisServiceImp
{
	/**
	 * 进行csv操作
	 */
	private DataOperatorInf csvOper = new DataCsvOperatorImpl();

	/**
	 * 进行主宾结构的解析
	 */
	private MatchRuleServiceInf matchMainGuest = new MatchRuleMainGuestServiceImpl();

	/**
	 * 进行主谓宾结构的解析
	 */
	private MatchRuleServiceInf matchMainCallGuest = new MatchRuleMainCallGuestServiceImpl();

	/**
	 * 生成主宾结构的SQL
	 */
	private MatchSqlServiceInf sqlMainGuest = new MatchSqlMainGuestlServiceImpl();

	/**
	 * 生成主谓宾结构的SQL
	 */
	private MatchSqlServiceInf sqlMainCallGuest = new MatchSqlMainCallGuestlServiceImpl();

	public Map<String, String> analysisAndGetSql(InputStream csvPath) throws PatternRuleException
	{
		Map<String, String> map = null;

		// 1,得到csv文件
		List<RuleCsvBean> csvRuleDataList = csvOper.readCsvFile(csvPath);

		if (null != csvRuleDataList && !csvRuleDataList.isEmpty())
		{
			map = new HashMap<String, String>();

			// 2,进行遍历解析
			for (RuleCsvBean parseCsvDataLine : csvRuleDataList)
			{
				// 首先使用,检查是否为1:主叫主动介绍,2：主叫主动介绍,如果是，则使用主宾结构进行解析生成SQL
				if (parseCsvDataLine.getType().equals(RoleMatchSystemParam.ROLE_KEY_TYPE_ONE.getKey()) || parseCsvDataLine.getType().equals(RoleMatchSystemParam.ROLE_KEY_TYPE_TWO.getKey()))
				{
					boolean ruleMatch = matchMainGuest.checkRuleMatch(parseCsvDataLine.getRuleinfo());

					// 如果规则验证成功则继续
					if (ruleMatch)
					{
						// 进行转化为bean对象
						MatchKey match = matchMainGuest.explain(parseCsvDataLine.getRuleinfo());

						// 进行SQL生成
						String sql = sqlMainGuest.getSql(match);

						map.put(parseCsvDataLine.getType(), sql);
					} else
					{
						throw new PatternRuleException("matcher :" + parseCsvDataLine.getType() + " is error");
					}
				} else if (parseCsvDataLine.getType().equals(RoleMatchSystemParam.ROLE_KEY_TYPE_THIRD.getKey()))
				{

					boolean ruleMatch = matchMainCallGuest.checkRuleMatch(parseCsvDataLine.getRuleinfo());

					// 如果规则验证成功则继续
					if (ruleMatch)
					{
						// 进行转化为bean对象
						MatchKey match = matchMainCallGuest.explain(parseCsvDataLine.getRuleinfo());

						// 进行SQL生成
						String sql = sqlMainCallGuest.getSql(match);

						map.put(parseCsvDataLine.getType(), sql);
					} else
					{
						throw new PatternRuleException("matcher :" + parseCsvDataLine.getType() + " is error");
					}

				}
			}
		} else
		{
			throw new PatternRuleException("csvfile read error!");
		}

		return map;

	}

	public static void main(String[] args)
	{
		String path = "D:\\java\\workspace\\java\\demo\\src\\main\\java\\com\\liujun\\mobile\\swindle\\doc\\模板.csv";

		CsvInputAnalysisServiceImp csvinput = new CsvInputAnalysisServiceImp();
		try
		{
			Map<String, String> repMap = csvinput.analysisAndGetSql(new FileInputStream(path));
			
			System.out.println(repMap);
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PatternRuleException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
