package com.jerryLog.www.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session.Cookie;
import org.springframework.stereotype.Service;

import com.jerryLog.www.bean.UserBean;
import com.jerryLog.www.components.SessionManager;
import com.jerryLog.www.dao.*;

@Service
public class LoginService {
	
	private Map<String, Object> returnMap;
	
	@Autowired
	IuserDAO userDao;
	
	@Autowired
	SessionManager sessionManager;
	
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
	 * @method 로그인
	 * @param userInfo
	 * @return forward register
	 */
	public Map<String, Object> login(UserBean userInfo) {
		
		returnMap = new HashMap<String, Object>();		
		UserBean userResult= userDao.getUserInfo(userInfo);
		
		if(userResult != null) {
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
	 * @return forward register
	 */
	public UserBean loginAuth(String email) {
		
		return userDao.getUserAuthInfo(email);
	}
	
	/**
	 * @method 회원가입
	 * @param userInfo
	 * @return
	 */
	public Map<String, Object> signup(UserBean userInfo) {
		
		returnMap = new HashMap<String, Object>();
		
		int result = userDao.putUserSignUp(userInfo);
		
		System.out.println(result);
		returnMap.put("result", "Success");
		
		return returnMap;
	}

}
