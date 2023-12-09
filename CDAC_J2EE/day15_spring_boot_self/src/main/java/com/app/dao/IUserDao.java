package com.app.dao;

import java.util.List;

import com.app.pojos.User;

public interface IUserDao {
	
	//add method for user login
	User validateUser(String email,String Password);
	
	//add method to return list of vendors
	List<User> getAllVendors();
	
	//delete user details
	String deleteUserDetails(User vendor) ;
	
	//find user details from it's PK
	User findByUserId(int userId); 
	
	//Register New Vendor
	String registerVendor(User transientVendor); 
}
