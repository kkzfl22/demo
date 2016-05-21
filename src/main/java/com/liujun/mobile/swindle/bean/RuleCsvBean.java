package com.liujun.mobile.swindle.bean;

import org.apache.commons.csv.CSVRecord;

import com.liujun.mobile.swindle.service.csv.ParseCsvDataLineInf;

public class RuleCsvBean implements ParseCsvDataLineInf<RuleCsvBean>
{
	/**
	 * 规则类型：1:主叫主动介绍,2：主叫主动介绍,3：广告推销
	 */
	private String type;

	/**
	 * 规则信息
	 */
	private String ruleinfo;

	public RuleCsvBean()
	{
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getRuleinfo()
	{
		return ruleinfo;
	}

	public void setRuleinfo(String ruleinfo)
	{
		this.ruleinfo = ruleinfo;
	}

	@Override
	public String[] parseCsvData()
	{
		return new String[] { type, ruleinfo };
	}

	@Override
	public RuleCsvBean toDataBean(CSVRecord lineMap)
	{
		RuleCsvBean user = new RuleCsvBean();

		if (lineMap != null)
		{
			user.setType(lineMap.get(0));
			user.setRuleinfo(lineMap.get(1));
		}

		return user;
	}

	@Override
	public String[] getColumn()
	{
		return new String[] { "规则类型：1:主叫主动介绍,2：主叫主动介绍,3：广告推销", "规则" };
	}

}
