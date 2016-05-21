package com.liujun.mobile.swindle.service.rule;

import com.liujun.mobile.swindle.bean.MatchKey;
import com.liujun.mobile.swindle.console.PatternRuleException;

/**
 * 进行规则匹配接口
 * 
 * @author liujun
 * @date 2016年3月5日
 * @verion 0.0.1
 */
public interface MatchRuleServiceInf
{
	/**
	 * 正则匹配验证规则
	 * @param data 规则数据
	 */
	public boolean checkRuleMatch(String data);

	/**
	 * 解释得到语法的规则
	 * 
	 * @param pattern
	 *            规则信息
	 * @return 解析后的数据
	 */
	public MatchKey explain(String pattern) throws PatternRuleException;
}
