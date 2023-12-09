package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Vendor;

public interface VendorRepository extends JpaRepository<Vendor,Integer> {

}
