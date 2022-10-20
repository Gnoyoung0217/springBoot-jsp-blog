package com.jerryLog.www.Model;

import java.sql.Timestamp;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserBean {
	
	private String email;
	private String name;
	private String password;
	private String phoneNum;
	private String secondPassword;
	private String region;
	private Timestamp creDate;
	private Timestamp modDate;

	public UserBean() {
	}
	
	public UserBean(String email, String name, String password, String phoneNum, String secondPassword, String region, Timestamp creDate, Timestamp modDate) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.phoneNum = phoneNum;
		this.secondPassword = secondPassword;
		this.region = region;
		this.creDate = creDate;
		this.modDate = modDate;
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

	/**
	 * 비밀번호 암호화
	 * @param passwordEncoder
	 * @return
	 */
	public UserBean hashPassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
		return this;
	}
	
	public boolean checkPassword(String plainPassword, PasswordEncoder passwordEncoder) {
		return passwordEncoder.matches(plainPassword, this.password);
		
	}
}
