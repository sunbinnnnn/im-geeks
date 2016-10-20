package com.imgeeks.common.bean;

import java.io.Serializable;
import java.util.List;

import com.imgeeks.base.BaseEntity;

public class User  extends BaseEntity  implements Serializable {
	
	public String headimg;

	public String username;
	public String realname;
	public int qq;
	public String address;
	public String phone;
	public String password;
	public int age;
	public String email;
	public String createtime;
	public String url;
	public String job;
	public List<UserSkill> userSkill;
	public List<UserSkill> getUserSkill() {
		return userSkill;
	}

	public void setUserSkill(List<UserSkill> userSkill) {
		this.userSkill = userSkill;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Role> roles;
	
	public String getHeadimg() {
		return headimg;
	}
	
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	
	public int getQq() {
		return qq;
	}

	public void setQq(int qq) {
		this.qq = qq;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getRealname() {
		return realname;
	}
	
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", age=" + age + "]";
	}
}
