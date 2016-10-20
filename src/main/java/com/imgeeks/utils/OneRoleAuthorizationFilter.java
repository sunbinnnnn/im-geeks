/*
 * Copyright (c) 2014, 2015, dhl and/or its affiliates. All rights reserved.
 * dhl PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.imgeeks.utils;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * 自定义角色过滤器 支持多个角色可以访问同一个资源
 * 
 * @author 云计算应用与开发项目组
 * @since V1.0
 * 
 */
public class OneRoleAuthorizationFilter extends AuthorizationFilter {
	@Override
	@SuppressWarnings({ "unchecked" })
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {

		Subject subject = getSubject(request, response);
		String[] rolesArray = (String[]) mappedValue;

		if (rolesArray == null || rolesArray.length == 0) {
			// no roles specified, so nothing to check - allow access.
			return true;
		}
		boolean flag = false;
		Set<String> roles = CollectionUtils.asSet(rolesArray);
		for (String string : roles) {
			if (subject.hasRole(string)) {
				flag = true;
			}
		}
		return flag;
	}
}
