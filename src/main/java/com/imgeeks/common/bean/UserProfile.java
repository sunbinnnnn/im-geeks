package com.imgeeks.common.bean;

import com.imgeeks.base.BaseEntity;

public class UserProfile extends BaseEntity{
	private User user;
	
	private String profile;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
}
