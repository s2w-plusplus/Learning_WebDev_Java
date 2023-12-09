package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.service.UserServiceImpl;


@Controller
public class HomePageController {

	@Autowired
	private UserServiceImpl userservice;
	
	public HomePageController(){
		System.out.println("in ctor of"+getClass().getName());
	}
	
	@GetMapping("/")
	public String showLoginPage() {
		System.out.println("in showHomePage()");
	return "/index";  
	}
	
	@PostMapping("/")
	public String processLoginPage(@RequestParam String email,@RequestParam String password) {
		System.out.println("in processHomePage()");
		userservice.authenticateNGetRole(email,password);
		
		
		
	return "/index";  
	}
	
}
