package com.liujun.framework.springsecurity.service;

import com.liujun.framework.springsecurity.bean.MyUser;

/**
 * 用户服务
 * @author Administrator
 *
 */
public interface MyUserServiceInf {
	
	
	/**
	 * 进行用户的登陆操作
	 * @param user 用户信息
	 * @return 结果信息
	 */
	public MyUser queryUserByLogin(MyUser user);
	
	
	/**
	 * 根据用户名查找用户信息
	 * @param user 用户名信息
	 * @return 结果信息
	 */
	public MyUser queryUserByUserName(String name);
	
	



}
