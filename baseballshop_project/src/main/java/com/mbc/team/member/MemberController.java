package com.mbc.team.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

	@RequestMapping(value = "/memberinput")
	public String member()
	{
		return "memberinput";
	}
	
}
