package com.solinvictus.SpringSecurityDemo.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailService;

	// For Authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("username 1")
//			.password("$2a$10$hdIGCIqPKzw2FVZpzJMlROoVbqcFShB8ty87IpScVTpMRl43b0xYa").roles("USER", "ADMIN");

		auth.userDetailsService(userDetailService);
	}

	// For Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN").anyRequest()
				.hasAnyRole("USER").and().formLogin().loginPage("/login.html") // for custom login page
				.usernameParameter("user").passwordParameter("password")
				.defaultSuccessUrl("/home").permitAll();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
