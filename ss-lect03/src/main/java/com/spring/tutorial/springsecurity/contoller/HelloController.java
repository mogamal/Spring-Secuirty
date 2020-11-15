package com.spring.tutorial.springsecurity.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.tutorial.springsecurity.dto.User;
import com.spring.tutorial.springsecurity.model.UserRquest;

@RestController
public class HelloController {

	@Autowired
	private UserDetailsManager userDetailsManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}

	@PostMapping("/user")
	public void addUser(@RequestBody UserRquest userRequest) {
		User user = new User(userRequest.getUsername(), userRequest.getPassword());
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		userDetailsManager.createUser(user);
	}
}
