package com.imgeeks.common.bean;

import java.io.Serializable;

import com.imgeeks.base.BaseEntity;

public class Role extends BaseEntity implements Serializable{

	private String rolename;
	
	private String rolecode;


	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}
}
