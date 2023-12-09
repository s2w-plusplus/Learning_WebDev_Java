package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotBlank;

@Entity
@PrimaryKeyJoinColumn(name = "admin_id")
public class Admin extends User{
	@NotBlank
	private String phoneNo;
	public Admin() {
		System.out.println("in admin ctor");
	}
	public Admin(String phoneNo) {
		super();
		this.phoneNo = phoneNo;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	@Override
	public String toString() {
		return "Admin "+super.toString()+" [phoneNo=" + phoneNo + "]";
	}
	
	

}
