package com.springsecurity.lec01.confi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig {

	@Bean
	public  UserDetailsService userDetailsService() {
		var userDetailsService  = new InMemoryUserDetailsManager();
		var userDetails = User.withUsername("bill")
									.password("12345")
									.authorities("read")
									.build();
//		UserDetailsService userDetailsService =(u)-> new User("","",null);
		userDetailsService.createUser(userDetails);
		return userDetailsService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
}
