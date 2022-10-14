package com.jerryLog.www.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jerryLog.www.SessionConst;
import com.jerryLog.www.bean.UserBean;

@Controller
@RequestMapping("/api/page")
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

//	@RequestMapping("/register")
//	public ModelAndView register() {
//		ModelAndView mav = new ModelAndView();
//		
//		mav.addObject("name", "용용");
//		
//		List<String> argList = new ArrayList<String>();
//		
//		argList.add("국어 : 100");
//		argList.add("국어 : 10");
//		
//		mav.addObject("argList",argList);
//		mav.setViewName("register");
//		return mav; 
//	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model) {

		return "login";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {

		// 로그아웃 처리
		// 세션이 있으면 세션 만료, 없으면 패스
		// getSession() : 디폴트는 true, false는 세션 없을때 null 반환
		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}

		return "login";
	}

	@RequestMapping("/signup")
	public String signup(Model model) {
		return "signup";
	}

	@RequestMapping({"/", "/home"})
	public String home(HttpServletRequest request, Model model) {
//		HttpSession session = request.getSession(false);
//
//		if (session == null) {
//			return "login";
//		}
//
//		UserBean userMember = (UserBean) session.getAttribute(SessionConst.LOGIN_MEMBER);
//		if (userMember == null) {
//			return "login";
//		}

		return "index";
	}

	@RequestMapping("/leftsidebar")
	public String leftsidebar(Model model) {
		return "left-sidebar";
	}

}
