package com.app.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.pojos.Role;
import com.app.pojos.User;
import com.app.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	//dependency : service layer i/f
	@Autowired
	private IUserService userService;
	
	public UserController() {
		System.out.println("in ctor of "+ getClass().getName()+" userv->"+ userService);
	} 
	
	@PostConstruct
	public void myInit()
	{
		System.out.println("in myInit of "+ getClass().getName()+" userv->"+ userService);
	}
	
	//add request handling method for showing the login form
	//key in the HandlerMapping Bean:/user/login:method:get
	@GetMapping("/login")
	public String showLoginForm() {
		return "/user/login"; // WEB_INF/views/user/login.jsp
	}
	
	//add request handling method for processing the login form
	//key in the HandlerMapping Bean:/user/login:method:post
	@PostMapping("/login")
	public String processLoginForm(@RequestParam String email, @RequestParam(name = "pass") String pwd 
			,Model map,HttpSession session )
	{
		System.out.println("in processLoginForm() with email: "+email+"password:"+pwd);
		//controller invoking service layer method
		try {
			User validatedUser = userService.validateUser(email, pwd);
			//valid login: add the validated user details under model map(saved in request scope)
			session.setAttribute("user_details", validatedUser);
			session.setAttribute("message","Login successful under role of"+validatedUser.getUserRole());
			//check role
			if(validatedUser.getUserRole().equals(Role.ADMIN))//admin logged in
				return "redirect:/admin/list";//sends temporary redirect response to client browser
			return "redirect:/vendor/details";
		}catch(RuntimeException re){
			re.printStackTrace();
			//invalid login-> forward client to login.jsp 
			map.addAttribute("message","Invalid Login! pls retry....");
			return "/user/login"; //actual view name /WEB-INF/views/user/login.jsp 
		}
		
	}
	
	//add new request handling method for logging the user out
	@GetMapping("/logout")
	public String userLogout(Model modelmap,HttpSession session
			,HttpServletRequest request,HttpServletResponse response) 
	{
		System.out.println("in userLogout()");
		modelmap.addAttribute("details", session.getAttribute("user_details"));
		//discard session
		session.invalidate();
		response.setHeader("refresh","5;url="+request.getContextPath());
		return "/user/logout"; //AVN: /WEB-INF/views/user/logout.jsp
	} 
	
	
}
