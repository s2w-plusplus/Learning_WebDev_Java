package com.app.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.User;

@Repository
public class UserDaoImpl {


	@Autowired
	private EntityManager manager;
	
	public User authenticateUser(String email,String password){
		String jpql = "select u from Course c where c.email=:courseTitle";
		return manager.createQuery(jpql, Course.class).setParameter("courseTitle", title).getSingleResult();
	}
	
}
