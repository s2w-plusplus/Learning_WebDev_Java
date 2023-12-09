package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

//name,email,password,regAmount,regDate(LocalDate)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)

@Table(name = "users_tbl")
public class User extends BaseEntity {
	@Column(length = 20)
	@NotBlank(message = "Name can't be blank")
	private String name;
	@Column(length = 20, unique = true)
	@NotBlank(message = "Email must be supplied")
	@Email(message = "Invalid email format")
	@Length(min = 5, max = 20, message = "Invalid email length")
	private String email = "abc@gmail.com";
	@Column(length = 20, nullable = false)
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Password either blank or invalid")
	private String password;
	// add a role
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Role role;

	public User() {
		System.out.println("in user ctor");
	}

	public User(String name, String email, String password, Role role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User id "+getId()+"[name=" + name + ", email=" + email + ", password=" + password + ", role=" + role + "]";
	}

}
