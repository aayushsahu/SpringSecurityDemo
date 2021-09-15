package com.solinvictus.SpringSecurityDemo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solinvictus.SpringSecurityDemo.Entity.User;

@RestController
public class HomeController {

	@GetMapping(name = "/home")
	public List<User> home(){
		User u1 = new User("Username 1" , "Password 1", "9555399742");
		User u2 = new User("Username 2" , "Password 2", "9555399743");
		User u3 = new User("Username 3" , "Password 3", "9555399744");
		
		List<User> users = new ArrayList<User>();
		users.add(u1);
		users.add(u2);
		users.add(u3);
		
		return users;
	} 
}
