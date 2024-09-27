package com.mbc.team.login;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class LoginController {
	
	
	@Autowired
	SqlSession sqlSession;
	
	
	
	@RequestMapping(value = "/login")
	public String login()
	{
		
		return "login";
	}
	
	@RequestMapping(value = "/logincheck")
	public String login1(HttpServletRequest request, Model mo)
	{
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		LoginService ls=sqlSession.getMapper(LoginService.class);
		LoginDTO dto3=ls.logincheck(id,pw);
		mo.addAttribute("dto3", dto3);
		if(dto3!=null)
		{
			HttpSession hs=request.getSession();
			hs.setAttribute("loginstate", true);
			hs.setAttribute("dto3", dto3);
			return "redirect:/main";
		}
		else
		{
			return "main";
		}
	}
	
	@RequestMapping(value = "/myinfo")
	public String member1(HttpServletRequest request, Model mo, HttpServletResponse response) throws IOException
	{
		HttpSession hs=request.getSession();
		boolean yy=(Boolean)hs.getAttribute("loginstate");
		LoginDTO dto3=(LoginDTO)hs.getAttribute("dto3");
		if(yy)
		{	
			LoginService ls=sqlSession.getMapper(LoginService.class);
			LoginDTO dto1=ls.myinfo(dto3.getId());
			mo.addAttribute("dto1",dto1);
			
			return "myinfo";
		}
		else
		{	response.setContentType("text/html;charset=utf-8");
			PrintWriter pww=response.getWriter();
			pww.print("<script> alert('로그인 후 이용해주새요')</script>");
			pww.print("<script> location.href='main'</script>");
			pww.close();
			return "redirect:/main";
		}
		
	}
	
	@RequestMapping(value = "/logout")
	public String login2(HttpServletRequest request)
	{
		HttpSession hs=request.getSession();
		hs.setAttribute("loginstate", false);
		hs.removeAttribute("dto3");
		return "redirect:/main";
	}
	
	
}
