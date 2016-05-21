package com.liujun.framework.springsecurity.service.impl.selfsecurity;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.liujun.framework.springsecurity.bean.MyUser;
import com.liujun.framework.springsecurity.service.MyUserServiceInf;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	/**
	 * 日志
	 */
	private Logger log = Logger.getLogger(MyUsernamePasswordAuthenticationFilter.class);

	public static final String VALIDATE_CODE = "validateCode";
	public static final String USERNAME = "j_username";
	public static final String PASSWORD = "j_password";

	/**
	 * 用户名信息
	 */
	private MyUserServiceInf myuserService;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		// 1，验证当前的请求是否为post请求
		if (!"POST".equals(request.getMethod())) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		HttpSession session = request.getSession();

		// 优化从sso中获取用户信息
		final Assertion principal = (Assertion) (session == null ? request.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION) : session
				.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION));

		Map<String, Object> map = null;
		if (null != principal) {

			map = principal.getPrincipal().getAttributes();

			if (null != map && !map.isEmpty()) {
				for (Entry<String, Object> item : map.entrySet()) {
					log.info("MyUsernamePasswordAuthenticationFilter info item, key:" + item.getKey() + ";value:" + item.getValue());
				}
			}
		}
		String username = null;
		String password = null;

		if (null != map && !map.isEmpty()) {
			username = String.valueOf(map.get("username"));
			password = String.valueOf(map.get("password"));
		} else {

			// 取得用户名和密码
			username = this.obtainUsername(request);
			password = this.obtainPassword(request);

			// 进行帐号和密码验证
			username = username.trim();

			MyUser user = new MyUser();
			user.setName(username);
			user.setPassword(password);

			user = myuserService.queryUserByLogin(user);

			// 如果当前用户登陆成功
			if (null == user || "".equals(user.getName())) {
				throw new AuthenticationServiceException("password or username is notEquals");
			}

		}

		// UsernamePasswordAuthenticationToken实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		// Place the last username attempted into HttpSession for views

		// 允许子类设置详细属性
		setDetails(request, authRequest);

		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		Object obj = request.getParameter(USERNAME);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		Object obj = request.getParameter(PASSWORD);
		return null == obj ? "" : obj.toString();
	}

	public void setMyuserService(MyUserServiceInf myuserService) {
		this.myuserService = myuserService;
	}

}
