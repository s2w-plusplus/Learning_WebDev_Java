package com.app.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.pojos.Location;
import com.app.pojos.PaymentMode;
import com.app.pojos.PaymentType;
import com.app.pojos.Vendor;

@SpringBootTest
class ServiceLayerTests {
	@Autowired
	private IVendorService vendorService;

	@Test
	void testRegisterVendor() {
		Vendor v=new Vendor(600,LocalDate.of(2015, 1, 1),
				new PaymentMode(PaymentType.CREDIT_CARD, 3456, LocalDate.now()));
		v.setEmail("mihir@gmail.com");
		v.setName("Mihir");
		v.setPassword("mih#123");
		Location location=new Location("pune", "mh", "in");
		location.setVendor(v);
		System.out.println(vendorService);
		String mesg = vendorService.registerVendor(location);
		System.out.println(mesg);
	}

}
