package com.app.controller;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //mandatory
@RequestMapping("/test") //optional but recommended
public class TestController {
	
	public TestController() {
		
		System.out.println("in ctor of "+getClass().getName());
		
	}

	@GetMapping("/test1") //=@RequestMapping:method=GET
	public ModelAndView testMe() 
	{
		
		System.out.println("in testMe()");
		//Return ModelView Object wrapping 
		//logical View Name and model attribute	
		//o.s.w.s.ModelAndView(String viewName,String modelAttrName,Object value)
		return new ModelAndView("/test/test1","server_date",LocalDateTime.now());
		//Handler ret M&V to the D.S
		//LogicalViewName: /test/test1, actual view name returned by the view resolver
		//WEB-INF/views/test/test1.jsp
	}
	
	//add request handling method for 
	//storing the results in the Model map
	//o.s.ui.Model i/f
	//it represents holder for model attributes
	@GetMapping("/test2")
	public String testMe2(ModelMap modelMap)//IOC 
	{
		System.out.println("in test model map"+modelMap);//{}Empty model map
		modelMap.addAttribute("server_date",LocalDateTime.now())
		.addAttribute("num_list", Arrays.asList(10,20,30,40));
		System.out.println("in test model map"+modelMap);//populated with 2 model attributes
		return "/test/test1";//Handler EXPLICITLY returns logical view name to F.C
		//Handler IMPLICITLY returns entire Model Map to F.C
	}
	
}
