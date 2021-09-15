package com.solinvictus.SpringSecurityDemo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.solinvictus.SpringSecurityDemo.Entity.User;
import com.solinvictus.SpringSecurityDemo.Repository.UserRepository;
import com.solinvictus.SpringSecurityDemo.Security.MyUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user;
		if(!username.equals("")) {
			user = userRepo.findByUsername(username);
			if(user==null)
				throw new UsernameNotFoundException("Username and or passsword not found in database");
		}
		else {
			throw new UsernameNotFoundException("Username cannot be blank");
		}
		return new MyUserDetails(user);
	}
	
}
