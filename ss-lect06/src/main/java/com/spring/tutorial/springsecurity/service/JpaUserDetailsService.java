package com.spring.tutorial.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.tutorial.springsecurity.model.User;
import com.spring.tutorial.springsecurity.respository.UserRepository;
import com.spring.tutorial.springsecurity.security.model.SecurityUser;


@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var o = userRepository.findUserByUsername(username);
		User user = o.orElseThrow(()-> new UsernameNotFoundException("error "));
		return new SecurityUser(user);
	}

}
