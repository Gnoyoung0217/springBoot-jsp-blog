package com.jerryLog.www.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerryLog.www.bean.UserBean;
import com.jerryLog.www.service.LoginService;

@RestController
@RequestMapping("/api/users")
public class LoginController {
	
	@Autowired
	LoginService loginSvc;

	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody UserBean userInfo) {

		System.out.println("Email ::: " + userInfo.getEmail());
		System.out.println("Password ::: " + userInfo.getPassword());
		
		return loginSvc.login(userInfo);
	}
	
	@PostMapping("/signup")
	public Map<String, Object> signup(@RequestBody UserBean userInfo) {
		
		System.out.println("Email ::: " + userInfo.getEmail());
		System.out.println("Name ::: " + userInfo.getName());
		System.out.println("PhoneNum ::: " + userInfo.getPhoneNum());
		System.out.println("Password ::: " + userInfo.getPassword());
		
		return loginSvc.signup(userInfo);
		
	}

}
