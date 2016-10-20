package com.imgeeks.common.bean;

import com.imgeeks.base.BaseEntity;

public class UserSkill extends BaseEntity {
	public void setUser(User user) {
		this.user = user;
	}

	private User user;
	
	private String proficiency;
	
	private String userskill;

	public User getUser() {
		return user;
	}

	public String getProficiency() {
		return proficiency;
	}

	public void setProficiency(String proficiency) {
		this.proficiency = proficiency;
	}

	public String getUserskill() {
		return userskill;
	}

	public void setUserskill(String userskill) {
		this.userskill = userskill;
	}
}
