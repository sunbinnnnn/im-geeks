package com.imgeeks.bean;

public class MessageBean {
	private boolean flag;
	
	private String message;
	
	private Object other;


	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public Object getOther() {
		return other;
	}

	public void setOther(Object other) {
		this.other = other;
	}

	public MessageBean(boolean flag, String message, Object other) {
		super();
		this.flag = flag;
		this.message = message;
		this.other = other;
	}

	public MessageBean(boolean flag, String message) {
		super();
		this.flag = flag;
		this.message = message;
	}
	

}
