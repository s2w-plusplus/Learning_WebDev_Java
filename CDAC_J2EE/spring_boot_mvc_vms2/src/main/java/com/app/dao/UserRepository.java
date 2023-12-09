package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	// add a method to authenticate user
		User findByEmailAndPassword(String em, String pass);

	//	@Query("select v from Vendor v left outer join fetch v.vendorLocation where v.role=:rl")
		//since it's uni dir from Location ---> Vendor , no need of join fetch 
	
//	  @Query("select v from Vendor v where v.role=:rl") List<User>
//	  listAllVendors(@Param("rl") Role role);
	 
		

		
}
