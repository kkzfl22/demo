package com.liujun.framework.springsecurity.service.impl;

import org.springframework.stereotype.Service;

import com.liujun.framework.springsecurity.service.ServiceInf;

@Service
public class ServiceImp implements ServiceInf {

	@Override
	public void testSecurityAll(String name) throws Exception {
		System.out.println("当前用户" + name + "，调用了testSecurityAll方法");
	}

	@Override
	public void testSecurityUser(String name) throws Exception {
		System.out.println("当前用户" + name + "，调用了testSecurityUser方法");
	}

}
