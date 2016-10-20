package com.imgeeks.common.bean;

import com.imgeeks.base.BaseEntity;

public class UserWorkHistory extends BaseEntity {
	private User user;
	
	private String worktitle;
	
	private String begintime;
	
	private String endtime;
	
	private String companyname;
	
	private String workhistory;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getWorktitle() {
		return worktitle;
	}

	public void setWorktitle(String worktitle) {
		this.worktitle = worktitle;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getWorkhistory() {
		return workhistory;
	}

	public void setWorkhistory(String workhistory) {
		this.workhistory = workhistory;
	}
}
