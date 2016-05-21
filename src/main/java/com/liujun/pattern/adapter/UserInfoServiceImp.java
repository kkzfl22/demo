package com.liujun.pattern.adapter;

import java.util.Map;

public class UserInfoServiceImp implements UserInfoServiceInf
{

	/**
	 * 用户家庭信息
	 */
	private final UserWebServiceHome userHome;

	/**
	 * 用户工作信息
	 */
	private final UseWebServiceWork userwork;

	public UserInfoServiceImp(UserWebServiceHome userHome, UseWebServiceWork userwork)
	{
		this.userHome = userHome;
		this.userwork = userwork;
	}

	@Override
	public Map<String, String> getUserinfo()
	{
		Map<String, String> map = this.userHome.getUserHome();

		map.putAll(this.userwork.getUserWork());

		return map;
	}

}
