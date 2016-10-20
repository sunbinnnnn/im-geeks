package com.imgeeks.common.dao;

import com.imgeeks.common.bean.UserProfile;

public interface UserProfileDao {

	public UserProfile getUserProfileByUserId(int id);

	public UserProfile getUserJbxxProfile(int userid);

}
