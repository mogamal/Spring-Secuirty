package com.spring.tutorial.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.spring.tutorial.springsecurity.authentication.provider.CustomAuthenticationProvider;
import com.spring.tutorial.springsecurity.filer.CustomerAuthenticationFilter;

@Configuration
public class ProjectConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomerAuthenticationFilter filter;
	@Autowired
	private CustomAuthenticationProvider authenticationProvider;

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAt(filter, BasicAuthenticationFilter.class);
		http.authorizeRequests().anyRequest().permitAll();
	}

}
