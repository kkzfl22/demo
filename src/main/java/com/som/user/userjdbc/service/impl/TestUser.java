package com.som.user.userjdbc.service.impl;

import com.som.user.userjdbc.bean.Userinfo;
import com.som.user.userjdbc.service.UserServiceInf;

public class TestUser
{
	public static void main(String[] args)
	{
		UserServiceInf userService = new UserServiceImp();

		Userinfo user = new Userinfo();

		user.setUsername("testkk2");
		user.setPassword("kk123");
		user.setSex(1);
		user.setMobile("13412345678");
		user.setHight(170);
		user.setAddress("宜昌");

		boolean addrsp =userService.addUser(user);
		System.out.println("添加结果:"+addrsp);
		
		
		boolean rsp = userService.checkUserName("testkk");
		System.out.println("用户是否存在:"+rsp);
		
		
		boolean loginRsp = userService.userLogin(user);
		
		System.out.println("登录结果:"+loginRsp);
	}
}
