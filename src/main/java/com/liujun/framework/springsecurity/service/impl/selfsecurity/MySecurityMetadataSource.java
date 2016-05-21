package com.liujun.framework.springsecurity.service.impl.selfsecurity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.liujun.framework.springsecurity.bean.MyResource;
import com.liujun.framework.springsecurity.service.MyResourceServiceInf;

/**
 * 加载资源与权限的对应关系
 * 
 * @author Administrator
 * 
 */
public class MySecurityMetadataSource
		implements
			FilterInvocationSecurityMetadataSource {

	public MySecurityMetadataSource(MyResourceServiceInf resourceService) {
		this.resourceService = resourceService;
		loadResourceDefine();
	}

	/**
	 * 加载资源服务
	 */
	private MyResourceServiceInf resourceService;

	/**
	 * 加载资源map信息
	 */
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	
	public MyResourceServiceInf getResourceService() {
		return resourceService;
	}

	public void setResourceService(MyResourceServiceInf resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * 返回请求资源所需要的权限
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {

		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		if (resourceMap == null) {
			loadResourceDefine();
		}
		
		
		return resourceMap.get(requestUrl);
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	/**
	 * 加载所有的资源信息
	 */
	public void loadResourceDefine() {
		if (null == resourceMap) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

			// 加载所有的资源信息
			List<MyResource> listRes = this.resourceService.queryResources();

			if (null != listRes && !listRes.isEmpty()) {
				for (MyResource myResource : listRes) {
					Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
					// 以权限名称封装spring的security的object
					ConfigAttribute configattri = new SecurityConfig(
							myResource.getName());
					configAttributes.add(configattri);
					resourceMap
							.put(myResource.getResString(), configAttributes);
				}
			}
		}
	}

}
