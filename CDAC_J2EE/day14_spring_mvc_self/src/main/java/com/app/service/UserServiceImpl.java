package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IUserDao;
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

}
