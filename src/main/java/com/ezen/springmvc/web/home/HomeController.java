package com.ezen.springmvc.web.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ezen.springmvc.domain.member.dto.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {
	
	@GetMapping("")
	public String home(@SessionAttribute(name="loginMember", required = false)Member loginMember,Model model,HttpServletRequest request) {
		if(loginMember != null) {
//			model.addAttribute("loginMember",loginMember);
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", loginMember);
		}
		return "index";
	}
}
