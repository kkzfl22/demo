package com.liujun.framework.springsecurity.service;

import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.liujun.framework.springsecurity.TestBaseJdbcSupperJunit;

public class TestUserDetailsService extends TestBaseJdbcSupperJunit {
	
	
	@Test
	public void testloadUserByUsername()
	{
		UserDetailsService userdetail = (UserDetailsService) this.getBean("myUserDetailsService");
		
		UserDetails user = userdetail.loadUserByUsername("liujun");
		
		System.out.println(user);
	}
}
