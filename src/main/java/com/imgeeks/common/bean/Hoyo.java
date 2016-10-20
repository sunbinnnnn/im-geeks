package com.imgeeks.common.bean;

import com.imgeeks.base.BaseEntity;

public class Hoyo extends BaseEntity {
	private int uid1;
	
	private int uid2;
	
	private String createdate;

	public int getUid1() {
		return uid1;
	}

	public void setUid1(int uid1) {
		this.uid1 = uid1;
	}

	public int getUid2() {
		return uid2;
	}

	public void setUid2(int uid2) {
		this.uid2 = uid2;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
}
