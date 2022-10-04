package com.jerryLog.www.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping("/signup")
	public String signup() {
		System.out.println("forwarding:signup");
		return "signup"; 
	}

}
