package com.liujun.framework.springsecurity.dao;

import java.util.List;

import com.liujun.framework.springsecurity.bean.MyUser;

public interface MyUserDaoInf {
	
	
	
	/**
	 * 根据用户id查询用户信息
	 * @param id id信息
	 * @return 用户信息
	 * @throws Exception 异常
	 */
	public List<MyUser> queryUserList(int id) throws Exception;
	
	
	
	/**
	 * 进行用户的登陆操作
	 * @param user 用户信息
	 * @return 结果信息
	 * @throws Excpetion
	 */
	public MyUser queryUserByLogin(MyUser user)throws Exception;
	
	
	/**
	 * 根据用户名查找用户信息
	 * @param user 用户名信息
	 * @return 结果信息
	 * @throws Excpetion
	 */
	public MyUser queryUserByUserName(String name)throws Exception;
	
	
	/**
	 * 插入用户信息
	 * @param user 用户信息
	 * @return 异常信息
	 * @throws Exception
	 */
	public int insertUser(MyUser user) throws Exception;
	
	

}
