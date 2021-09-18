package com.solinvictus.SpringSecurityDemo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.solinvictus.SpringSecurityDemo.Entity.User;
import com.solinvictus.SpringSecurityDemo.Repository.UserRepository;

@Service
public class AdminServices {
	
	@Autowired
	UserRepository userRepo;
	
	@Secured({ "ROLE_ADMIN" })
	public List<User> getAllUserAccounts(){
		
		List<User> users  = userRepo.findAll();
		return users;
	}
	
}
