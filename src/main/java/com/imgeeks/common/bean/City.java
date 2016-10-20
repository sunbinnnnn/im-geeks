package com.imgeeks.common.bean;

import com.imgeeks.base.BaseEntity;

public class City  extends BaseEntity {
	private String cityName;
	
	private int pid;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
}
