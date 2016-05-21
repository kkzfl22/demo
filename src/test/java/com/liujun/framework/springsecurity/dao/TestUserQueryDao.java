package com.liujun.framework.springsecurity.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.liujun.framework.springsecurity.TestBaseJdbcSupperJunit;
import com.liujun.framework.springsecurity.bean.MyUser;

public class TestUserQueryDao extends TestBaseJdbcSupperJunit {

	@Test
	public void queryUserList() throws Exception {

		List<MyUser> list = ((MyUserDaoInf) this.getBean("myUserDaoImpl"))
				.queryUserList(1);

		System.out.println(list);

		Assert.assertNotNull(list);
	}
	
	@Test
	public void userLogin()throws Exception
	{
		MyUser user = new MyUser();
		user.setName("liujun");
		user.setPassword("liujun");
		
		user = ((MyUserDaoInf) this.getBean("myUserDaoImpl")).queryUserByLogin(user);
		
		System.out.println("user2:"+user);
		Assert.assertNotNull(user);
	}
}
