package com.imgeeks.common.bean;

import com.imgeeks.base.BaseEntity;

public class UserProject extends BaseEntity{
	private User user;
	
	private String author;
	
	private String workimg;
	
	private String worktitle;
	
	private String url;
	
	private String date;
	
	private String statu;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getWorkimg() {
		return workimg;
	}

	public void setWorkimg(String workimg) {
		this.workimg = workimg;
	}

	public String getWorktitle() {
		return worktitle;
	}

	public void setWorktitle(String worktitle) {
		this.worktitle = worktitle;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}
}
