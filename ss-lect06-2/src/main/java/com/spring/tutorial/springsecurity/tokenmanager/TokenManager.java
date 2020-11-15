package com.spring.tutorial.springsecurity.tokenmanager;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class TokenManager {

	private Set<String> tokenManager = new HashSet<>();

	public boolean add(String token) {
		return tokenManager.add(token);
	}

	public boolean contains(String token) {
		return tokenManager.contains(token);
	}
}
