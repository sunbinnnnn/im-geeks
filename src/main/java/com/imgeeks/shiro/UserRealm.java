/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.imgeeks.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.imgeeks.common.service.UserService;
import com.imgeeks.common.service.impl.UserServiceImpl;
import com.imgeeks.common.bean.User;

/**
 * 用户realm
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
public class UserRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;

	/**
	 * 授权操作
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		String username = (String) getAvailablePrincipal(principals);
		String username = (String) principals.getPrimaryPrincipal();
		
//		List<UserRole> urlist = userService.getUserRole(username);
		
//		Set<Role> roleSet = userService.findUserByUsername(username).getRoleSet();
		//角色名的集合
		Set<String> roles = new HashSet<String>();
		//权限名的集合
		Set<String> permissions = new HashSet<String>();
		
//		for (UserRole ur:urlist)
//		{
//			String rolekey = ur.getRole().getRolekey();
//			roles.add(rolekey);
//		}
//		Iterator<Role> it = roleSet.iterator();
//		while(it.hasNext()){
//			roles.add(it.next().getName());
////			for(Permission per:it.next().getPermissionSet()){
////				permissions.add(per.getName());
////			}
//		}
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		authorizationInfo.addRoles(roles);
		authorizationInfo.addStringPermissions(permissions);
		
		
		return authorizationInfo;
	}

	/**
	 * 身份验证操作
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String email = (String) token.getPrincipal();
		User user = userService.getUserByEmail(email);
		
		if(user==null){
			//木有找到用户
			throw new UnknownAccountException("帐号不存！");
		}
		/* if(Boolean.TRUE.equals(user.getLocked())) {  
	            throw new LockedAccountException(); //帐号锁定  
	        } */
		
		/**
		 * 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现  
		 */
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getEmail(), user.getPassword(),getName());
		
		return info;
	}
	
	@Override
	public String getName() {
		return getClass().getName();
	}

}
