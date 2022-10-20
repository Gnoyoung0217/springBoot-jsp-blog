package com.jerryLog.www.controller;

import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

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
	public String login(HttpServletRequest request, Model model,Authentication authentication) {
		HttpSession session = request.getSession();
		System.out.println("페이지에서 받은 세션 값 :: " + session.getAttribute("userEmailSession"));
		
		if (authentication != null) {
			UserDetails userDetail = (UserDetails) authentication.getPrincipal();
			System.out.println("Security가 가지고 있는 세션 값 :: " + userDetail.getUsername());

			// object
			ObjectMapper objMapper = new ObjectMapper();
			Map<String, Object> map = objMapper.convertValue(authentication.getDetails(), Map.class);
			System.out.println("Security가 가지고 있는 세션 ID :: " + map.get("sessionId")); // authentication의 세션 아이디

			// authentication의 권한
			Iterator<? extends GrantedAuthority> iter = userDetail.getAuthorities().iterator();
			if (iter.hasNext()) {
				if (iter.next().toString().equals("USER")) {
					System.out.println("authority :: USER");
					return "redirect:/";
				}
			}
		}

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

		return "index";
	}

	@RequestMapping("/leftsidebar")
	public String leftsidebar(Model model) {
		return "left-sidebar";
	}

}
