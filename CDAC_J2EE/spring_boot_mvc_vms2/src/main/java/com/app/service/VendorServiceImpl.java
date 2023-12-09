package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.LocationRepository;
import com.app.dao.VendorRepository;
import com.app.pojos.Location;
import com.app.pojos.Role;
import com.app.pojos.Vendor;

@Service // mandatory : to tell SC following class is a B.L supplier.
@Transactional // mandatory : to tell Spring supplied tx mgr bean , to handle txs
				// automatically.
public class VendorServiceImpl implements IVendorService {
	// dependency : DAO layer
	@Autowired
	private VendorRepository vendorRepo;
	@Autowired
	private LocationRepository locationRepo;

	@Override
	public List<Vendor> listAllVendors() {
		// TODO Auto-generated method stub
		return vendorRepo.findAll();
	}

	@Override
	public String deleteVendorDetails(int vendorId) {
		//checking if location details have been set up for this vendor : if yes then deleting locstion
		//details 1st as it's the parent record n then vendor details
		if (locationRepo.existsById(vendorId)) {
			System.out.println("location found ....");
			locationRepo.deleteById(vendorId);
		}
		vendorRepo.deleteById(vendorId);
		return "Vendor details deleted successfully , for ID=" + vendorId;

	}

	@Override
	public String registerVendor(Location location) {
		Vendor v = location.getVendor();
		// set role
		v.setRole(Role.VENDOR);
		System.out.println(vendorRepo.save(v));
		System.out.println(locationRepo.save(location));
		return "Vendor reged with ID " + v.getId();
	}

	@Override
	public Vendor getVendorDetails(int vendorId) {
		// TODO Auto-generated method stub
		return vendorRepo.findById(vendorId)
				.orElseThrow(() -> new RuntimeException("Vendor details not found for id " + vendorId));
	}

	@Override
	public String updateVendorDetails(Vendor vendor) {
		Vendor updatedVendor = vendorRepo.save(vendor);
		return "Updated vendor details for vendor with ID=" + updatedVendor.getId();
	}

}
