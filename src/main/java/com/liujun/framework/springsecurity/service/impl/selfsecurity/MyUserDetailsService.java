package com.liujun.framework.springsecurity.service.impl.selfsecurity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.liujun.framework.springsecurity.bean.MyResource;
import com.liujun.framework.springsecurity.bean.MyRole;
import com.liujun.framework.springsecurity.bean.MyUser;
import com.liujun.framework.springsecurity.service.MyResourceServiceInf;
import com.liujun.framework.springsecurity.service.MyRoleServiceInf;
import com.liujun.framework.springsecurity.service.MyUserServiceInf;

/**
 * 根据用户名得到用户的信息
 * 
 * 用户拥有的权限加载
 * 
 * @author Administrator
 * 
 */
public class MyUserDetailsService implements UserDetailsService {

	/**
	 * 用户信息
	 */
	@Autowired
	private MyUserServiceInf userService;

	/**
	 * 用户权限信息
	 */
	@Autowired
	private MyRoleServiceInf myroleService;

	/**
	 * 用户资源管理
	 */
	@Autowired
	private MyResourceServiceInf myResourceSercie;
	
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		MyUser user = userService.queryUserByUserName(username);

		if (null == user) {
			throw new NullPointerException("MyUserDetailsService  loadUserByUsername user is null");
		}

		// 得到用户所对应的权限信息
		Set<GrantedAuthority> grantList = this.getRole(user);

		boolean enables = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		// 对当前用户进行授权
		User userdetail = new User(user.getName(), user.getPassword(), enables, accountNonExpired, credentialsNonExpired, accountNonLocked, grantList);

		return userdetail;
	}

	/**
	 * 加载当前用户对应的资源信息
	 * 
	 * @param user
	 * @return
	 */
	private Set<GrantedAuthority> getRole(MyUser user) {

		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();

		// 得到用户的权限信息
		if (null != user) {
			user = myroleService.getRoleByUserId(user);

			// 加载用户的权限信息
			List<MyRole> roleList = user.getUserRoles();

			if (null != roleList && !roleList.isEmpty()) {
				for (MyRole myRole : roleList) {
					// 加载资源信息
					myRole = myResourceSercie.queryRoleResouce(myRole);

					// 加载资源信息
					List<MyResource> resourceList = myRole.getResourceS();
					
					//加入所拥有的权限信息
					if (null != resourceList && !resourceList.isEmpty()) {
						for (MyResource myResource : resourceList) {
							authSet.add(new SimpleGrantedAuthority(myResource.getName()));
						}
					}
				}
			}
		}

		return authSet;
	}

}
