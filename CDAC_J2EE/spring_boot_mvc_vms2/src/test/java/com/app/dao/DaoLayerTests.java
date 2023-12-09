package com.app.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.pojos.User;

@SpringBootTest
class DaoLayerTests {
	@Autowired
	private UserRepository userRepo;

	@Test
	void testFindByEmailAndPassword() {
	//	User user = userRepo.findByEmailAndPassword("rama@gmail.com", "1234");
		User user = userRepo.findByEmailAndPassword("kiran@gmail.com", "2234");
		System.out.println(user);
		System.out.println(user.getClass());
	}
	

}
