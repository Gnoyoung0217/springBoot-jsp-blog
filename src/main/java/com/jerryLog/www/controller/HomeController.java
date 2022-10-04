package com.jerryLog.www.controller;

import java.util.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {
	
	
	@RequestMapping("/home")
	public List<String> home() {
		return Arrays.asList("Hello","Home");
	}

}
