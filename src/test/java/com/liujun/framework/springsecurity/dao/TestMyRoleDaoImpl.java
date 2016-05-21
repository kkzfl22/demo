package com.liujun.framework.springsecurity.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.liujun.framework.springsecurity.TestBaseJdbcSupperJunit;
import com.liujun.framework.springsecurity.bean.MyRole;

public class TestMyRoleDaoImpl extends TestBaseJdbcSupperJunit {

	@Test
	public void testqueryRoles() throws Exception {
		List<MyRole> listRole = ((MyRoleDaoInf) this.getBean("myRoleDaoImpl"))
				.queryRoles();
		System.out.println(listRole);
		Assert.assertNotNull(listRole);
	}

}
