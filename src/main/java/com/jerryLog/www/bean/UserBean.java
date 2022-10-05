package com.jerryLog.www.bean;

import java.sql.Timestamp;

public class UserBean {
	private String email;
	private String name;
	private String password;
	private String phoneNum;
	private String secondPassword;
	private String region;
	private Timestamp creDate;
	private Timestamp modDate;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getSecondPassword() {
		return secondPassword;
	}

	public void setSecondPassword(String secondPassword) {
		this.secondPassword = secondPassword;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Timestamp getCreDate() {
		return creDate;
	}

	public void setCreDate(Timestamp creDate) {
		this.creDate = creDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}

	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}

}
