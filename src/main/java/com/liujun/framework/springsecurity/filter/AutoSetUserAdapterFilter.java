package com.liujun.framework.springsecurity.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;

/**
 * Servlet implementation class AutoSetUserAdapterFilter
 */
public class AutoSetUserAdapterFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		// _const_cas_assertion_是CAS中存放登录用户名的session标志
		Object object = httpRequest.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
		
		
		AttributePrincipal principal = (AttributePrincipal)httpRequest.getUserPrincipal();
		
		Map<String, Object> map  = null;
		if (null != principal) {

			map = principal.getAttributes();

			if (null != map && !map.isEmpty()) {
				for (Entry<String, Object> item : map.entrySet()) {
					System.out.print("UsernamePasswordAuthenticationFilter info item, key:" + item.getKey() + ";value:" + item.getValue());
				}
			}
		}
		
		
		if (object != null) {
			Assertion assertion = (Assertion) object;
			String loginName = assertion.getPrincipal().getName();
			System.out.println("用户已经在SSO系统中登录，登录用户名为:" + loginName);
			String user = getCurrentUser(httpRequest);
			// 第一次登录系统
			if (user == null) {
				System.out.println("用户第一次登录系统,保存session信息.");
				httpRequest.getSession().setAttribute("User_Info", loginName);
			} else {
				System.out.println("当前Session中的用户信息为:" + user);
			}

		}
		chain.doFilter(request, response);
	}

	private String getCurrentUser(HttpServletRequest request) {
		return (String) request.getSession().getAttribute("User_Info");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}
