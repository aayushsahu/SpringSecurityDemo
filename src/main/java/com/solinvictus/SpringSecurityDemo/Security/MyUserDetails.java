package com.solinvictus.SpringSecurityDemo.Security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.solinvictus.SpringSecurityDemo.Entity.Authorities;
import com.solinvictus.SpringSecurityDemo.Entity.User;

public class MyUserDetails extends User implements UserDetails{

	public MyUserDetails(User user) {
		this.setAuthorities(user.getAuthorities());
		this.setUserId(user.getUserId());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setMobileNo(user.getMobileNo() );
		
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
