package com.spring.tutorial.springsecurity.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.spring.tutorial.springsecurity.filter.AuthenticationFilter;
import com.spring.tutorial.springsecurity.filter.TokeAuthenticationFilter;

@SuppressWarnings("all")
@Configuration
public class ProjectConfiguration extends WebSecurityConfigurerAdapter {

	@Qualifier("usernamePasswordAuthenticationProvider")
	@Autowired
	private AuthenticationProvider usernamePasswordAuthenticationProvider;

	@Qualifier("otpAuthenticationProvider")
	@Autowired
	private AuthenticationProvider otpAuthenticationProvider;

	@Qualifier("tokenAuthenticationProvider")
	@Autowired
	private AuthenticationProvider tokenAuthentication;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAt(this.authenticationFilter(), BasicAuthenticationFilter.class)
				.addFilterAfter(tokenAuthenticationFilter(), AuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(this.usernamePasswordAuthenticationProvider)
				.authenticationProvider(this.otpAuthenticationProvider)
				.authenticationProvider(this.tokenAuthentication);
	}

	@Bean
	public TokeAuthenticationFilter tokenAuthenticationFilter() {
		return new TokeAuthenticationFilter();
	}

	@Bean
	public AuthenticationFilter authenticationFilter() {
		return new AuthenticationFilter();
	}
}
