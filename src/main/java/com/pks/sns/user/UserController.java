package com.pks.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/snsuser")
public class UserController {
	
	
	@GetMapping("/signin/view")
	public String singinView() {
		return "/user/signin";
	}
	
	@GetMapping("/signup/view")
	public String singupView() {
		return "/user/signup";
	}
	
	
	@GetMapping("/signout")
	public String signout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		
		return "redirect:/snsuser/signin/view";
	}
	
	
}
