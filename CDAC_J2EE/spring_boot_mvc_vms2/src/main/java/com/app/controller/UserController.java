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
	// dependency : service layer i/f
	@Autowired
	private IUserService userService;

	public UserController() {
		System.out.println("in ctor " + getClass().getName());
	}
	//add init style method to display the dependency
	@PostConstruct
	public void anyMethod()
	{
		System.out.println("in init of "+getClass().getName()+" "+userService);
	}

	@GetMapping("/login")
	public String showLoginForm() {
		System.out.println("in show login form");
		return "/user/login";// actual view name : /WEB-INF/views/user/login.jsp
	}

	// add a req handling method to process the form

	@PostMapping("/login")
	public String processLoginForm(@RequestParam String email, @RequestParam String password,
			Model map,HttpSession session) {
		System.out.println("in process login form " + email + " " + password);
		// invoke service's method for validation
		try {
			User validatedUser = userService.authenticateUser(email, password);
			// successful login
			// add a mesg Admin/Vendor login successful
			session.setAttribute("message", validatedUser.getRole() + " login successful!");
			//add validated user details in session scope , to remember it till logout
			session.setAttribute("user_details", validatedUser);
			// if user : vendor => redirect the clnt to vendor details page
			if (validatedUser.getRole().equals(Role.VENDOR))
				return "redirect:/vendor/details";
			// SC(WC) response.sendRedirect(response.encodeRedirectURL("/vendor/details"));
			
			// if user : admin => redirect the clnt to vendor list page 
		
			return "redirect:/admin/list";//UserController ---> redirect view name ---> FC
			//SC repsonse.sendRedirect(response.encideRedirectURL("/admin/list"));
			//tmp resp is sent to clnt : SC 302 | location=/admin/list;jsessionid=dgsdf456345| empty content
			//clnt sends NEXT request http://host:port/spring-mvc/boot/admin/list;jsessionid=dgsdf456345

		} catch (RuntimeException e) {
			e.printStackTrace();
			// failed login
			// add err mesg as the model attribute
			map.addAttribute("message", "Invalid Login , Please Retry");
			// forward the client to login page , highlighted with error mesg.
			return "/user/login";// actual view name : /WEB-INF/views/user/login.jsp
		}

	}
	//add a common method for user(vendor|admin) logout
	@GetMapping("/logout")
	public String logout(HttpSession session,Model map,HttpServletRequest request,HttpServletResponse resp)
	{
		System.out.println("in logout "+map);
		//get user details from session scope n add it under current request scope
		map.addAttribute("details",session.getAttribute("user_details"));
		//invalidate session
		session.invalidate();
		//How to auto navigate the clnt to the next page (eg : home page) after a dly ?
		//Method of HttpServletRespose : public void setHeader(String name,String value)
		resp.setHeader("refresh", "5;url="+request.getContextPath());
		return "/user/logout";//actual view name /WEB-INF/views/user/logout.jsp 
	}
	

}
