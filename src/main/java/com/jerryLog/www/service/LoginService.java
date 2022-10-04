package com.jerryLog.www.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jerryLog.www.bean.UserBean;

@Service
public class LoginService {
	
	/**
	 * @method 로그인
	 * @param userInfo
	 * @return
	 */
	public Map<String, Object> login(UserBean userInfo) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		returnMap.put("methodName", new Object() {}.getClass().getEnclosingMethod().getName());
		returnMap.put("result", "Success");
		
		return returnMap;
	}
	
	/**
	 * @method 회원가입
	 * @param userInfo
	 * @return
	 */
	public Map<String, Object> signup(UserBean userInfo) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		// dao로 db 접근
		
		returnMap.put("methodName", new Object() {}.getClass().getEnclosingMethod().getName());
		returnMap.put("result", "Success");
		
		return returnMap;
	}
}
