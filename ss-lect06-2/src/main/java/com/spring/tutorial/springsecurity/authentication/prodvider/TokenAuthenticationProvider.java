package com.spring.tutorial.springsecurity.authentication.prodvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.spring.tutorial.springsecurity.authentication.TokenAuthentication;
import com.spring.tutorial.springsecurity.tokenmanager.TokenManager;

@Component("tokenAuthenticationProvider")
public class TokenAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private TokenManager tokenManager;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String token = authentication.getName();
		boolean exist = tokenManager.contains(token);

		if (exist) {
			return new TokenAuthentication(token, null);

		}
		throw new BadCredentialsException("Invalid token!");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return TokenAuthentication.class.equals(authentication);
	}

}
