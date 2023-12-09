package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.pojos.User;
import com.app.service.IUserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	//dependency: service layer
	@Autowired
	private IUserService userService;
	
	public AdminController() {
		System.out.println("in ctor of"+getClass().getName());
	}
	
	//add new request handling method to list all vendors
	@GetMapping("/list")
	public String showVendors(Model modelMap) {
		System.out.println("in showVendors()");
		modelMap.addAttribute("vendor_list", userService.getAllVendors());
		return "/admin/list"; //AVN: /WEB-INF/views/admin/list.jsp
	}
	
	//add new request mapping to handle vendor deletion
	@GetMapping("/delete")
	public String deleteVendor(@RequestParam int vid,RedirectAttributes flashMap) {
		System.out.println("in deleteVendor() with vendorId:"+vid);
		//invoke service layer method
		flashMap.addFlashAttribute("message", userService.deleteVendorDetails(vid));
		return "redirect:/admin/list"; //redirects the client , sends temporary resp
	}
	
	//add request handling method :for showing vendor registration form 
	@GetMapping("/add")
	public String showVendorRegForm(User vendor) //POJO-->Form data binding
	//SC creates vendor instance using default ctor and adds the POJO in the Model map
	{
		System.err.println("in showVendorRegForm()");		
		return "/admin/add";//AVN: WEB-INF/views/admin/add.jsp
	}
	
	//add request handling method :for processing vendor registration form 
	@PostMapping("/add")
	public String processVendorRegForm(RedirectAttributes flashMap,User userDetails) //Form data -->POJO binding
	{
		System.err.println("in processVendorRegForm() with "+userDetails);//populated POJO bound with formdata
		flashMap.addFlashAttribute("message",userService.registerVendor(userDetails));
		return "redirect:/admin/list"; 
	}
	
	
	//@GetMapping("/update")
	
	
}
