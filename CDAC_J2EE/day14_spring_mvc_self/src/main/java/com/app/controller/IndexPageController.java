package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexPageController {

	public IndexPageController() {
		
		System.out.println("in ctor of "+ getClass().getName() );
	}
	
	@RequestMapping("/")
	public String ShowIndexPage() {
		System.out.println("in ShowIndexPage() method");
		return "/index";//logical view name,
		//actual view name:WEB-INF/views/index.jsp
		
	}
}
