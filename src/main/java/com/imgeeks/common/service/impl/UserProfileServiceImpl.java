package com.imgeeks.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imgeeks.common.bean.UserProfile;
import com.imgeeks.common.dao.UserProfileDao;
import com.imgeeks.common.service.UserProfileService;
@Service
public class UserProfileServiceImpl implements UserProfileService {
	@Autowired
	private UserProfileDao userProfileDao;

	@Override
	public UserProfile getUserProfileByUserId(int id) {
		return userProfileDao.getUserProfileByUserId(id);
	}

	@Override
	public UserProfile getUserJbxxProfile(int userid) {
		return userProfileDao.getUserJbxxProfile(userid);
	}
}
