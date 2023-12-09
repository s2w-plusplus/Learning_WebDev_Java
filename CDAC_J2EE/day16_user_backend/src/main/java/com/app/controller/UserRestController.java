package com.app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ErrorResponse;
import com.app.pojos.User;
import com.app.service.IUserService;

@RestController // To tell SC , following class serves as the REST API endpoint (=@Controller
				// class level + @ResponseBody added over ret types of all req handling methods
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserRestController {
	// dependency : service layer i/f
	@Autowired
	private IUserService userService;

	public UserRestController() {
		System.out.println("in ctor of " + getClass().getName());

	}

	// add REST request handling method to ret list of all users.
	@GetMapping
	public List<User> getAllUsers() {
		System.out.println("in get all users");
		return userService.fetchAllUsers();
	}

	// add REST end point to save user info
	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		System.out.println("in save user " + user);// user : not null , except id
		// try {
		return new ResponseEntity<>(userService.saveUserDetails(user), HttpStatus.CREATED);
//		} catch (RuntimeException e) {
//			System.out.println("err in save "+e);
//			ErrorResponse resp=new ErrorResponse("Adding User failed!!!!!", LocalDateTime.now());
//			return new ResponseEntity<>(resp,HttpStatus.CONFLICT);
//		}
	}

	// add REST end point for deleting user details
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUserDetails(@PathVariable int userId) {
		System.out.println("in del user details " + userId);
		return ResponseEntity.ok(userService.deleteUserDetails(userId));
	}

	// add REST end point for getting user details
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserDetails(@PathVariable int id) {
		System.out.println("in get usr dtls " + id);
		return ResponseEntity.ok(userService.getUserDetails(id));
	}
}
