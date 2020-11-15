package com.spring.tutorial.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestContoller {
	
	@GetMapping("/hello")
	public String hello()
	{
		return "Hello 06-02";
	}


}
