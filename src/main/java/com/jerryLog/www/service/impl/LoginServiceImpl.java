package com.jerryLog.www.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerryLog.www.Model.UserBean;
import com.jerryLog.www.dao.*;
import com.jerryLog.www.security.SecurityConfig;
import com.jerryLog.www.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	private Map<String, Object> returnMap;
	
	@Autowired
	IuserDAO userDao;
	
	@Autowired
	UserBean userResult;
	
	/**
	 * @method 유저목록 전체조회
	 * @param userInfo
	 * @return
	 */
	public Map<String, Object> list() {
		
		returnMap = new HashMap<String, Object>();
		
		// dao로 db 접근
		List<UserBean> userList = userDao.getUserList();
				
		returnMap.put("userList", userList);
		returnMap.put("result", "Success");
		
		return returnMap;
	}
	
	/**
	 * @method 로그인 유효성 검사
	 * @param userInfo
	 * @return forward register
	 */
	public Map<String, Object> loginValidChk(UserBean userInfo) {
		
		returnMap = new HashMap<String, Object>();		
		userResult = userDao.getUserInfo(userInfo);
		
		if(userResult  != null) {
			returnMap.put("result", "Success");
		} else {
			returnMap.put("result", "Fail");
		}
		
		return returnMap;
	}
	
	/**
	 * @method 로그인 유효성 검사
	 * @param userInfo
	 * @return forward register
	 */
	public Map<String, Object> login(UserBean userInfo) {
		
		returnMap = new HashMap<String, Object>();		
		userResult = userDao.getUserInfo(userInfo);
		boolean flag = userResult.checkPassword(userInfo.getPassword(), SecurityConfig.bCryptPasswordEncoder());		
		
		System.out.println("flag 결과 ::: " + flag);
		if(userResult  != null && flag) {
			returnMap.put("userResult", userResult);
			returnMap.put("result", "Success");
		} else {
			returnMap.put("result", "Fail");
		}
		
		return returnMap;
	}
	
	/**
	 * @method 로그인
	 * @param userInfo
	 * @return 
	 */
	public UserBean loginAuth(String email) {
		
		UserBean userResult = userDao.getUserAuthInfo(email);
		
		return userResult; 
	}
	
	/**
	 * @method 회원가입
	 * @param userInfo
	 * @return
	 * @throws Exception 
	 */
	public Map<String, Object> signup(UserBean userInfo) {
		
		returnMap = new HashMap<String, Object>();
		
		UserBean userResult = loginAuth(userInfo.getEmail());
		if(userResult != null) {
			returnMap.put("result", "Fail");
			returnMap.put("message", "계정이 이미 존재합니다.");
			return returnMap;
		}
		
		userInfo.hashPassword(SecurityConfig.bCryptPasswordEncoder());
		
		int result = userDao.putUserSignUp(userInfo);
		
		System.out.println(result);
		returnMap.put("result", "Success");
		
		return returnMap;
	}
}
