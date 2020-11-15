package com.springsecurity.lec01.service;

import java.util.Optional;

import com.springsecurity.lec01.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import com.springsecurity.lec01.repository.UserDetailsRepository;

public class JPAUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userDetailsRepository.findByUsername(username);
//		user.orElseThrow(()->new new UsernameNotFoundException("Error!"));
		User u = user.orElseThrow(() -> new UsernameNotFoundException("Error!"));
		return new SecurityUser(u);
	}

}
