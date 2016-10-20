package com.imgeeks.common.bean;

import com.imgeeks.base.BaseEntity;

public class UserAttention extends BaseEntity {

	private int userid;

	private int otherid;

	private String createdate;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getOtherid() {
		return otherid;
	}

	public void setOtherid(int otherid) {
		this.otherid = otherid;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

}
