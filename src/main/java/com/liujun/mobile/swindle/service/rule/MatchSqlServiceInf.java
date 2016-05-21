package com.liujun.mobile.swindle.service.rule;

import com.liujun.mobile.swindle.bean.MatchKey;

/**
 * 生成SQL语句的接口信息
 * 
 * @author liujun
 * @date 2016年3月5日
 * @verion 0.0.1
 */
public interface MatchSqlServiceInf
{
	/**
	 * 生成SQL信息
	 * 
	 * @param bean
	 *            语法解析的结果
	 * @return 生成的sql信息
	 */
	public String getSql(MatchKey bean);
}
