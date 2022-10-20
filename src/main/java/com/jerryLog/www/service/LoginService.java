package com.jerryLog.www.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.jerryLog.www.Model.UserBean;

@Service
public interface LoginService {
	
	public Map<String, Object> list();
	public Map<String, Object> loginValidChk(UserBean userInfo);
	public Map<String, Object> login(UserBean userInfo);
	public UserBean loginAuth(String email);
	public Map<String, Object> signup(UserBean userInfo);
}
