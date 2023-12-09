package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.app.dao.UserRepository;
import com.app.pojos.User;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public User authenticateUser(String em, String pass) {
		

//		return userRepo.findByEmailAndPassword(em, pass)
//				.orElseThrow(() -> new RuntimeException("Invalid Email or password"));
		return userRepo.findByEmailAndPassword(em, pass);
	}

}
