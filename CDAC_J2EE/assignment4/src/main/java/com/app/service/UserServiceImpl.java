package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.UserDaoImpl;
import com.app.pojos.Role;
import com.app.pojos.User;

import custom_exception.CustomXception;

@Service
@Transactional
public class UserServiceImpl {

	@Autowired
	private UserDaoImpl userDao;
	
	public Role authenticateNGetRole(String email,String password) {
		
		User user=userDao.authenticateUser(email,password);
		if(user!=null)
			return user.getRole();
		throw new CustomXception("Invalid Email or Password");
	}
	
	
}
