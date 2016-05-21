package com.liujun.framework.springsecurity.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liujun.framework.springsecurity.service.ServiceInf;

@Controller
@RequestMapping(value = "/security")
public class SecurityContextGet {

	/**
	 * 服务接口
	 */
	@Autowired
	private ServiceInf service;

	/**
	 * 得到用户名的方法
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getusername.do")
	public void getUsername(HttpServletRequest request, HttpServletResponse response) {
		SecurityContext sexTx = SecurityContextHolder.getContext();
		Authentication auth = sexTx.getAuthentication();

		Object obj = auth.getPrincipal();

		if (obj instanceof UserDetails) {
			String username = ((UserDetails) obj).getUsername();
			System.out.println(username);
		} else {
			String name = obj.toString();
			System.out.println(name);
		}
	}

	@RequestMapping("testall.do")
	public void testall(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		service.testSecurityAll(name);

		PrintWriter write = response.getWriter();

		write.append("this is call testall.do request;");
	}

	@RequestMapping("tesuser.do")
	public void testuser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		service.testSecurityUser(name);

		PrintWriter write = response.getWriter();

		write.append("this is call tesuser.do request;");

	}

}
