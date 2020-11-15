package com.spring.tutorial.springsecurity.authentication.prodvider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.spring.tutorial.springsecurity.authentication.UsernamePasswordAuthentication;
import com.spring.tutorial.springsecurity.service.JpaUserDetailsService;

@Component("usernamePasswordAuthenticationProvider")
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private JpaUserDetailsService jpaUserDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = String.valueOf(authentication.getCredentials());
		UserDetails user = jpaUserDetailsService.loadUserByUsername(username);
		if (passwordEncoder.matches(password, user.getPassword())) {
			return new UsernamePasswordAuthentication(username, password, user.getAuthorities());
		}

		throw new BadCredentialsException("Bad Credential!");

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthentication.class.equals(authentication);
	}

}
