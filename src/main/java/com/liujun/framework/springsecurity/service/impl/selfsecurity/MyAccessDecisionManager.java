package com.liujun.framework.springsecurity.service.impl.selfsecurity;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 用户是否拥有所请求资源的权限
 * 
 * @author Administrator
 * 
 */
public class MyAccessDecisionManager implements AccessDecisionManager {

	/**
	 * 日志
	 */
	private Logger log = Logger.getLogger(MyAccessDecisionManager.class);

	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		if (configAttributes == null) {
			return;
		}

		// 所请求的资源拥有的权限(一个资源对多个权限)
		Iterator<ConfigAttribute> iter = configAttributes.iterator();

		while (iter.hasNext()) {
			ConfigAttribute configAttribute = iter.next();
			// 访问所请求资源所需要的权限
			String needPermission = configAttribute.getAttribute();
			log.info("MyAccessDecisionManager decide needPermission is:"
					+ needPermission);

			for (GrantedAuthority ga : authentication.getAuthorities()) {
				// 如果当前的请求资源在权限列表中,则可以通过当前的请求
				if (needPermission.equals(ga.getAuthority())) {
					return;
				}
			}
		}

		// 没有权限
		throw new AccessDeniedException("curr is not request");

	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return false;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
