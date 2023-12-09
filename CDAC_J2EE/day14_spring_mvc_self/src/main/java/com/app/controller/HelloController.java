package com.app.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //mandatory to tell SC:following is a spring bean 
			//meant for request handling controller
//def scope: singleton
//def loading policy: eager
public class HelloController {
	
	public HelloController() {
		System.out.println("in ctor of "+getClass().getName());
	}
	
	//init style method
	@PostConstruct
	public void init() 
	{
		System.out.println("in init");
	}
 
	//how to tell SC whatever follows is request handling method
	@RequestMapping("/hello")
	public String sayHello() {
		System.out.println("in sayHello()");
		return "/welcome";//what is it? -> logical view name
	}
 

}
