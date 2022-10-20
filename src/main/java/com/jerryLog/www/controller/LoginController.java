package com.jerryLog.www.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerryLog.www.Model.UserBean;
import com.jerryLog.www.service.LoginService;

@RestController
@RequestMapping("/api/users")
public class LoginController {
	
	@Autowired
	LoginService loginSvc;

	@PostMapping("/list")
	public Map<String, Object> list() {
		
		return loginSvc.list();
	}
	
	@PostMapping("/loginValidChk")
	public Map<String, Object> loginValidChk(HttpServletRequest request, @RequestBody UserBean userInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map = loginSvc.loginValidChk(userInfo);
		return map;
	}
	
	@PostMapping("/login")
	public Map<String, Object> login(HttpServletRequest request, @RequestBody UserBean userInfo) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		
		return map;
	}
	 
	@PostMapping("/logout")
	public void logout() {
	}
	
	@PostMapping("/signup")
	public Map<String, Object> signup(@RequestBody UserBean userInfo) throws Exception {
		
		return loginSvc.signup(userInfo);
		
	}
	

}
