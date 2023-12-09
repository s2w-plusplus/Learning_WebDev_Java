package pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //mandatory
@Table(name = "vendor_table")
public class Vendor {
	
	@Id//mandatory : PK constraint
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Vendor_ID")
	private Long id;
	@Column(name = "Name",length = 20) 
	private String name;
	@Column(name = "Email",length = 20,unique = true) 
	private String email;
	@Column(name = "Password",length = 20,nullable = false) 
	private String password;
	@Column(name="Reg_Amount")
	private double regAmount;
	@Column(name="Reg_Date")
	private LocalDate regDate;
 	
	public Vendor() {
		System.out.println("Inside default constructor of: "+getClass().getName());
	}

	public Vendor(Long id, String name, String email, String password, double regAmount, LocalDate regDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.regAmount = regAmount;
		this.regDate = regDate;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public double getRegAmount() {
		return regAmount;
	}
	public void setRegAmount(double regAmount) {
		this.regAmount = regAmount;
	}
	public LocalDate getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Vendor [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", regAmount="
				+ regAmount + ", regDate=" + regDate + "]";
	}
}
