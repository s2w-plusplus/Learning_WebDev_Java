package dao;

import java.util.List;

import pojos.Vendor;

public interface IVendorDAO {

	public String addVendorDetails(Vendor vendor);
	public Vendor findByVendorId(long vendorId); 
	public List<Vendor> getAllVendors();
}
