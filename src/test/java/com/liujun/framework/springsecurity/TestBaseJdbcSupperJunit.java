package com.liujun.framework.springsecurity;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestBaseJdbcSupperJunit {

	public Object getBean(String name) {
		// ApplicationContext context = new FileSystemXmlApplicationContext(
		// "classpath*:spring/springMVC-dao.xml");
		ApplicationContext context = new FileSystemXmlApplicationContext(
				"classpath*:spring/spring-*.xml");

		Object result = context.getBean(name);

		return result;

	}

}
