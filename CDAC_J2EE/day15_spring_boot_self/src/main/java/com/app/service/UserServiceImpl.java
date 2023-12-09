package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IUserDao;
import com.app.pojos.Role;
import com.app.pojos.User;

@Service //to tell SC:class holds B.L 
@Transactional
public class UserServiceImpl implements IUserService {
//dependency : DAO interface
	
	@Autowired
	private IUserDao userDao;
	
	@Override
	public User validateUser(String email, String password) {
		//invoke dao method
		System.out.println("in service layer where "
				+ "email: "+email+" and password: "+password);
		return userDao.validateUser(email, password);
	}

	@Override
	public List<User> getAllVendors() {
		System.out.println("in getAllVendors() of service layer");
		return userDao.getAllVendors();
	}

	@Override
	public String deleteVendorDetails(int vid) {
		//get user details from dao layer
		User vendor=userDao.findByUserId(vid);
		if(vendor!=null)
			return userDao.deleteUserDetails(vendor);
		return "Vendor deletion failed!!";
	}

	@Override
	public String registerVendor(User transientVendor) {
		transientVendor.setUserRole(Role.VENDOR);
		return userDao.registerVendor(transientVendor);
	}
}
