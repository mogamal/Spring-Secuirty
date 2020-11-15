package com.spring.tutorial.springsecurity.filter;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.tutorial.springsecurity.authentication.OTPAuthentication;
import com.spring.tutorial.springsecurity.authentication.UsernamePasswordAuthentication;
import com.spring.tutorial.springsecurity.model.Otp;
import com.spring.tutorial.springsecurity.repository.OtpRepository;
import com.spring.tutorial.springsecurity.tokenmanager.TokenManager;

public class AuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private OtpRepository otpRepository;

	@Autowired
	private TokenManager tokenManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String username = request.getHeader("username");
		String password = request.getHeader("password");
		String otp = request.getHeader("otp");
		if (null != request.getHeader("Authorization")) {
			filterChain.doFilter(request, response);
		}
		if (null == otp) {
			authenticationManager.authenticate(new UsernamePasswordAuthentication(username, password));

			Otp otpEntity = new Otp();
			otpEntity.setUsername(username);
			otpEntity.setOtp(String.valueOf(new Random().nextInt(9999) + 1000));

			otpRepository.save(otpEntity);

		} else {
			authenticationManager.authenticate(new OTPAuthentication(username, otp, List.of(() -> "read")));
			String token = UUID.randomUUID().toString();
			tokenManager.add(token);
			response.setHeader("Authorization", token);
		}

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getServletPath().equals("/login");
	}
}
