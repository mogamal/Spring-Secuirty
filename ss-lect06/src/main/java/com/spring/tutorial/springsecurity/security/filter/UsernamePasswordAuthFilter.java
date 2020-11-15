package com.spring.tutorial.springsecurity.security.filter;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.tutorial.springsecurity.model.Otp;
import com.spring.tutorial.springsecurity.respository.OtpRepository;
import com.spring.tutorial.springsecurity.security.authentications.OtpAuthentication;
import com.spring.tutorial.springsecurity.security.authentications.UsernamePasswordAuthentication;

@Component
public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

	@Autowired
	private AuthenticationManager authenicationManager;

	@Autowired
	private OtpRepository otpRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String username = request.getHeader("username");
		String password = request.getHeader("password");
		String otp = request.getHeader("otp");
		if (null == otp) {
			UsernamePasswordAuthentication usernamePasswordAuthentication = new UsernamePasswordAuthentication(username,
					password);
			Authentication authenication = this.authenicationManager.authenticate(usernamePasswordAuthentication);

			String generateOtp = String.valueOf(new Random().nextInt(9999) + 1000);

			Otp otpEntity = new Otp();
			otpEntity.setOtp(generateOtp);
			otpEntity.setUsername(username);
			this.otpRepository.save(otpEntity);
		} else {
			OtpAuthentication otpAuthentication = new OtpAuthentication(username, otp);
			authenicationManager.authenticate(otpAuthentication);
			response.addHeader("Authorization", UUID.randomUUID().toString());

		}
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getServletPath().equals("/login");
	}
}