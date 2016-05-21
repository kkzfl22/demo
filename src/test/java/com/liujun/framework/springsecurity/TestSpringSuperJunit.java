package com.liujun.framework.springsecurity;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/spring-*.xml")
public class TestSpringSuperJunit {

	@Autowired
	// 使用名称注入
	@Qualifier("dataSource")
	private DataSource dataSource;

	@Test
	public void query() throws Exception {

		int timeout = dataSource.getLoginTimeout();
		System.out.println(timeout);

		Assert.assertNotNull(timeout);
	}
}
