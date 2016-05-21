package com.liujun.pattern.adapter;

import java.util.Map;

public class TestMain
{
	public static void main(String[] args)
	{
		UserWebServiceHome userHome = new UserWebServiceHome();
		UseWebServiceWork userwork = new UseWebServiceWork();
		
		UserInfoServiceInf user = new UserInfoServiceImp(userHome, userwork);
		
		Map<String,String> map  = user.getUserinfo();
		
		System.out.println(map);
	}
}
