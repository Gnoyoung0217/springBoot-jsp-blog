package com.jerryLog.www.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerryLog.www.SessionConst;
import com.jerryLog.www.bean.UserBean;
import com.jerryLog.www.service.LoginService;

@RestController
@RequestMapping("/api/users")
public class LoginController {
	
	@Autowired
	LoginService loginSvc;

	@PostMapping("/list")
	public Map<String, Object> list(@RequestBody UserBean userInfo) {
		
		return loginSvc.list(userInfo);
	}
	
	@PostMapping("/login")
	public Map<String, Object>  login(HttpServletRequest request, @RequestBody UserBean userInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map = loginSvc.login(userInfo);
		if(map.get("result").equals("Success")) {
			// 로그인 성공 처리
			// 세션이 있으면 세션 반환, 없으면 신규 세션을 생성해서 반환
			// getSession() : 디폴트는 true, false는 세션 없을때 null 반환
			HttpSession session = request.getSession();
			session.setAttribute(SessionConst.LOGIN_MEMBER, map.get("userResult"));
		}
		return map;
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
