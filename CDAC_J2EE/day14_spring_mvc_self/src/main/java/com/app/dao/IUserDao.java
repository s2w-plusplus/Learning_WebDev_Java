package com.app.dao;

import com.app.pojos.User;

public interface IUserDao {
	
	//add method for user login
	User validateUser(String email,String Password);

}
