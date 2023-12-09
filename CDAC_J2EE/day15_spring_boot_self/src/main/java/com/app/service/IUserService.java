package com.app.service;

import java.util.List;

import com.app.pojos.User;

public interface IUserService {
	
	User validateUser(String email, String password);
	
	List<User> getAllVendors();
	
	String deleteVendorDetails(int vendorId);

	String registerVendor(User transientVendor); 

}