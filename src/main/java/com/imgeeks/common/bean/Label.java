package com.imgeeks.common.bean;

import com.imgeeks.base.BaseEntity;

public class Label extends BaseEntity {
	private String labelname;
	private int labelcode;
	private int statu;
	public String getLabelname() {
		return labelname;
	}
	public void setLabelname(String labelname) {
		this.labelname = labelname;
	}
	public int getLabelcode() {
		return labelcode;
	}
	public void setLabelcode(int labelcode) {
		this.labelcode = labelcode;
	}
	public int getStatu() {
		return statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}
}
