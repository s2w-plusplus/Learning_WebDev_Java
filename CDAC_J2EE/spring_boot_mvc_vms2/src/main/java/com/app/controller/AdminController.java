package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.pojos.Location;
import com.app.pojos.PaymentType;
import com.app.pojos.Vendor;
import com.app.service.IVendorService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	// dependency : service layer i/f
	@Autowired
	private IVendorService vendorService;

	public AdminController() {
		System.out.println("in ctor of " + getClass().getName());
	}

	// add a req handling method to list all vendors
	@GetMapping("/list")
	public String listVendors(Model map) {
		System.out.println("in list vendors...");
		map.addAttribute("vendor_list", vendorService.listAllVendors());
		return "/admin/list";// actual view name : /WEB-INF/views/admin/list.jsp
	}

	// add a req handling method to delete a specific vendor n REDIRECT client to
	// list page
	@GetMapping("/delete")
	public String deleteVendorDetails(@RequestParam int vid, RedirectAttributes flashMap) {
		System.out.println("in del vendor dtls " + vid);
		// invoke service layer method
		// Adding an attribute under flash scope (visible till the next request coming
		// from SAME clnt)
		flashMap.addFlashAttribute("message", vendorService.deleteVendorDetails(vid));
		return "redirect:/admin/list";
		// what will be the URL of the next request :
		// http://localhost:7070/spring-mvc-boot/admin/list
	}

	// add a request handling method to show registration form for a new vendor
	// to enable : 2 way form binding : add EMPTY Vendor pojo instance in the model
	// map
	@GetMapping("/register")
	public String showVendorRegistrationForm(Vendor v, Model map) {
		System.out.println("in show reg form " + v);
		// binding one more model map attribute to show payment types to user (via a
		// drop down list)
		map.addAttribute("modes", PaymentType.values());
//		System.out.println("map "+map);//populated map : 2 attributes -- Vendor & modes
		return "/admin/register";// actual view name : /WEB-INF/views/admin/register.jsp
	}

	// add a request handling method for accepting payment mode info : no longer
	// needed , since accepting
	// payment mode info in 1st page of the vendor registration.
//	@PostMapping("/payment_details_form")
//	public String showPaymentForm(Vendor v,Model map,HttpSession session,PaymentMode mode)
//	{
//		System.out.println("in show payment form "+v);
//		System.out.println(mode);
//	
//		System.out.println(map);
//		session.setAttribute("vendor", v);
//		map.addAttribute("modes", PaymentType.values());
//	
//		return "/admin/payment_form";//actual view name : /WEB-INF/views/admin/payment_form.jsp
//	}

	// add a request handling method to show the form for accepting location info of
	// the vendor
	// Vendor's basic info is injected in the 1st argument.
	@PostMapping("/location_form")
	public String showLocationForm(Vendor v, BindingResult result, HttpSession session, Location location) {
		System.out.println("in show location form " + v);// core vendor details
		// System.out.println(v.getMode());
		// saving vendor & it's payment info under session scope , since it has to be
		// retained till the next request. (alternatively can be added in flash scope )
		// BindingResult will be later used for presentation logic (P.L) validation
		session.setAttribute("vendor", v);
		// Can't invoke service layer method still : since Vendor's location info is not
		// yet set.
		return "/admin/location_form";// actual view name : /WEB-INF/views/admin/location_form.jsp
	}

	// add a request handling method for processing reg form
	// Complete vendor details are available now for registration (including core
	// details, payment mode + location)
	@PostMapping("/register")
	public String processRegFrom(Location location, BindingResult result, RedirectAttributes flashMap,
			HttpSession session) {
		System.out.println("in process reg form " + location);
		Vendor v = (Vendor) session.getAttribute("vendor");
		// If it's a bi-dir one-one relationship between Vendor <----> Location , then
		// use helper method
		// to set up a bi-dir link
		// v.addLocation(location);//IMPORTANT : setting a bi-dir link between Vendor n
		// location
		// BUT since using Vendor <----- Location , to reduce no of DB queries
		// changed bi dir to uni dir : helper method is not required.
		// Assigning vendor details in location pojo
		location.setVendor(v);
		System.out.println("Vendor " + v);
		// System.out.println(v.getMode());
		// System.out.println(v.getVendorLocation());
		// System.out.println(v.getVendorLocation().getVendor());
		flashMap.addFlashAttribute("message", vendorService.registerVendor(location));
		return "redirect:/admin/list";
//			return "/admin/display"; good way of testing before invoking service method n inserting data in DB
	}

	// add req handling method to show update form
	@GetMapping("/update")
	public String showVendorUpdateForm(@RequestParam int vid, Model map, HttpSession session) {
		System.out.println("in show update form " + vid + " " + map);
		// invoke service layer method , get detached POJO from service layer n bind it
		// to form data
		// Model ---> View layer binding
		Vendor v = vendorService.getVendorDetails(vid);
		map.addAttribute("vendor", v);
		// add it in the session , to be available in the next request (scope can be
		// alternatively narrowed to flash )
		session.setAttribute("vendor", v);
		System.out.println("fetched details from DB " + v);
		return "/admin/update";
	}

	// add req handling method for processing update form
	@PostMapping("/update")
	public String processUpdateForm(Vendor vendor, RedirectAttributes flashMap, HttpSession session) {
		// vendor : DETACHED POJO containing state , updated by client
		// BUT it does not have any updates for role & payment modes
		System.out.println("process update form " + vendor);
		// get vendor's existing details from session
		Vendor existingDetails = (Vendor) session.getAttribute("vendor");
		// merge the changes (updates) , retaining earlier role n payment mode values
		/*
		 * Method of o.s.beans.BeanUtils
		 * public static void copyProperties(Object source, Object
		 * target, String... ignoreProperties) throws BeansException
		 * Copy the property values of the given source bean into the given target
		 * bean,ignoring the given "ignoreProperties".
		 * 
		 */
		//copying the changes from vendor ---> existingDetails , by ignoring role & mode
		BeanUtils.copyProperties(vendor, existingDetails, "role", "mode");
		System.out.println("modified details " + existingDetails);
		System.out.println(vendor);
		flashMap.addFlashAttribute("message", vendorService.updateVendorDetails(existingDetails));
		return "redirect:/admin/list";
	}

}
