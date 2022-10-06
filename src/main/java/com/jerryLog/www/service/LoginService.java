package com.jerryLog.www.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerryLog.www.bean.UserBean;

import com.jerryLog.www.dao.*;

@Service
public class LoginService {
	
	private Map<String, Object> returnMap;
	
	@Autowired
	IuserDAO userDao;	
	
	/**
	 * @method 유저목록 전체조회
	 * @param userInfo
	 * @return
	 */
	public Map<String, Object> list(UserBean userInfo) {
		
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
		
		List<UserBean> userList = userDao.getUserInfo(userInfo);
		
		
		if(userList.size() > 0) {
			returnMap.put("userList", userList);			
			returnMap.put("result", "Success");
		} else {
			returnMap.put("result", "Fail");
		}
		
		return returnMap;
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
