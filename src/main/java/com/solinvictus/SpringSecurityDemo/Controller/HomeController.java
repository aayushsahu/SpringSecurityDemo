package com.solinvictus.SpringSecurityDemo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.solinvictus.SpringSecurityDemo.Entity.User;
import com.solinvictus.SpringSecurityDemo.Repository.UserRepository;
import com.solinvictus.SpringSecurityDemo.Service.AdminServices;

@RestController
public class HomeController {

	@Autowired
	AdminServices adminServices;

	@GetMapping(path = "/home")
	public @ResponseBody User home(@AuthenticationPrincipal User user) {
		return user;
	}
	
	@GetMapping(path="/admin/home")
	public @ResponseBody List<User> adminHome(@AuthenticationPrincipal User user) {
		List<User> users = adminServices.getAllUserAccounts();
		return users;
	}
	

}
