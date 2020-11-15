package com.spring.tutorial.springsecurity.authentication.prodvider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.spring.tutorial.springsecurity.authentication.OTPAuthentication;
import com.spring.tutorial.springsecurity.repository.OtpRepository;

@Component("otpAuthenticationProvider")
public class OtpAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private OtpRepository repository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String otp = String.valueOf(authentication.getCredentials());
		var otpEntity = repository.findByusername(username);

		if (otpEntity.isPresent()) {
			return new OTPAuthentication(username, otp, List.of(() -> "read"));
		}
		throw new BadCredentialsException("Invalid Otp !");

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return OTPAuthentication.class.equals(authentication);
	}

}
