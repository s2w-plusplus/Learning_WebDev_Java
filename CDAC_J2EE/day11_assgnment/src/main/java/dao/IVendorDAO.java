package dao;

import java.time.LocalDate;

import pojos.Vendor;

public interface IVendorDAO {

	public String addVendorDetails(Vendor vendor);
	public Vendor findByVendorId(long vendorId); 
	
	public String authenticateVendor(String email,String pwd);
	public String updateRegAmount(String email,double offset);
	public String deleteByCondition(double minAmount,LocalDate thresholdDate);
}
