package com.spring.tutorial.springsecurity.authentication;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UsernamePasswordAuthentication extends UsernamePasswordAuthenticationToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5789943319113370173L;

	public UsernamePasswordAuthentication(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public UsernamePasswordAuthentication(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

}
