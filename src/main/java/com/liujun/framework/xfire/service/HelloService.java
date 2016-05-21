package com.liujun.framework.xfire.service;

import java.rmi.RemoteException;

import com.liujun.framework.xfire.bean.User;

public interface HelloService {

	/**
	 * 初始测试方法
	 * 
	 * @param name
	 *            名称信息
	 * @return 返回结果信息
	 * @throws RemoteException
	 *             异常
	 */
	public String helloword(String name) throws RemoteException;

	/**
	 * 通过用户的id得到用户信息
	 * 
	 * @param id
	 *            用户id
	 * @return 用户信息
	 * @throws RemoteException
	 *             异常信息
	 */
	public User getUserById(int id) throws RemoteException;

}
