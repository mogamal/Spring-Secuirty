package com.spring.tutorial.springsecurity.filer;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.spring.tutorial.springsecurity.authentication.manager.CustomAuthenticationManager;

@Component
public class CustomerAuthenticationFilter implements Filter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		var httpServletRquest = (HttpServletRequest) request;
		var httpServletResponse = (HttpServletResponse) response;
		String authorization = httpServletRquest.getHeader("Authorization");

		var authentication = new CustomAuthenticationManager(authorization, null);
		try {
			Authentication result = this.authenticationManager.authenticate(authentication);
			if (result.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(result);
				chain.doFilter(request, response);
			}
		} catch (Throwable e) {
			httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}

	}
}
