package com.imgeeks.common.service;

import com.imgeeks.common.bean.UserProfile;

public interface UserProfileService {
	public UserProfile getUserProfileByUserId(int id);

	public UserProfile getUserJbxxProfile(int userid);
}
