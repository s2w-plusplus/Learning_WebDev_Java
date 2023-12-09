package com.app.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.*;

import com.app.pojos.Role;
import com.app.pojos.User;

@Repository //to tell SC:following class contains data access logic 
public class UserDaoImpl implements IUserDao {
	//Dependency:  javax.persistence.EntityManager(super i/f of Hibernate.Session)
	@Autowired
	private EntityManager manager;
	
	@Override
	public User validateUser(String email, String password) {
		String jpql = "select u from User u where u.email=:em and u.password=:pass";
		
		return manager.createQuery(jpql,User.class)
				.setParameter("em",email).setParameter("pass",password)
				.getSingleResult();
	}

	@Override
	public List<User> getAllVendors() {
		System.out.println("in getAllVendors() of dao layer");
		String jpql="select u from User u where u.userRole=:role";
		return manager.createQuery(jpql, User.class).setParameter("role", Role.VENDOR).getResultList();
	}
	
	@Override
	public String deleteUserDetails(User vendor) {
		
		String messg="Vendor details removed with vendorId:"+vendor.getId();
		manager.remove(vendor);
		return messg;
	}

	@Override
	public User findByUserId(int userId) {
	
		return manager.find(User.class,userId);
	}

	@Override
	public String registerVendor(User transientVendor) {
		manager.persist(transientVendor);
		return "Vendor registered successfully with Id:"+ transientVendor.getId();
	}
	
	
	

}
