package com.liujun.framework.springsecurity.service;

import org.springframework.security.access.annotation.Secured;

public interface ServiceInf {
	
	/**
	 * 当前接口用来测试权限
	 * @param name
	 * @throws Exception
	 */
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	public void testSecurityAll(String name)throws Exception;
	
	/**
	 * 当前接口用来测试单个权限访问问题
	 * @param name
	 * @throws Exception
	 */
	@Secured({"ROLE_USER"})
	public void testSecurityUser(String name)throws Exception;

}
