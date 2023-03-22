package com.solinvictus.SpringSecurityDemo;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
	@Test
	public void passwordEncoderDemoTest() {
		System.out.println(new BCryptPasswordEncoder().encode("password1"));
		System.out.println(new BCryptPasswordEncoder().encode("password2"));
		System.out.println(new BCryptPasswordEncoder().encode("password3"));
	}
}
