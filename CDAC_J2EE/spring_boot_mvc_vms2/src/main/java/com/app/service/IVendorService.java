package com.app.service;

import java.util.List;

import com.app.pojos.Location;
import com.app.pojos.Vendor;

public interface IVendorService {

	// add a method to return a vendor list
	List<Vendor> listAllVendors();

	// add a method to delete vendor details
	String deleteVendorDetails(int vendorId);

	// add a method to register new vendor details
	String registerVendor(Location loc);

	// add a method to find vendor details by id
	Vendor getVendorDetails(int vendorId);
	
	//add a method to update vendor details
	// i/p : detached vendor pojo , containing changes.
	String updateVendorDetails(Vendor vendor);
}
