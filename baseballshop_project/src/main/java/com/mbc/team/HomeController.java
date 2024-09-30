package com.mbc.team;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(value = "/")
	public String home(HttpServletRequest requset) {
		
		HttpSession hs= requset.getSession();
		hs.setAttribute("loginstate", false);
			
		return "main";
	}
	
	@RequestMapping(value = "/main")
	public String home1() {
		
		
		
		return "main";
	}
	
	
	
	
	
}
