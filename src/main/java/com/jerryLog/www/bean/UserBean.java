package com.jerryLog.www.bean;

public class UserBean {
	private String email;
	private String name;
	private String password;
	private String phoneNum;
	
	
	public UserBean(String email, String name, String password, String phoneNum) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.phoneNum = phoneNum;
	}
	
	
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
	
}
