package com.solinvictus.SpringSecurityDemo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	
	@GetMapping(path  = "/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login.html");
		return mv;
	}
}
