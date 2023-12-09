package pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name = "Vendor_bankAcc")
public class Vendor extends BaseEntity {
	
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
 	
	//inverse side,parent side
	@OneToMany(mappedBy = "acctOwner", cascade = CascadeType.ALL , orphanRemoval = true) 
	private List<BankAccount> acctList=new ArrayList<>();
 	
	public Vendor() {
		System.out.println("Inside default constructor of: "+getClass().getName());
	}
	
	public Vendor(String name, String email, String password, double regAmount, LocalDate regDate) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.regAmount = regAmount;
		this.regDate = regDate;
	}

	//convenience method helper method
	public void addAccount(BankAccount bankAcct) {
		acctList.add(bankAcct);
		bankAcct.setAcctOwner(this);
	}
	public void removeAccount(BankAccount bankAcct) {
		acctList.remove(bankAcct);
		bankAcct.setAcctOwner(null);
	}

	//all getters & setters
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
	public List<BankAccount> getAcctList() {
		return acctList;
	}
	public void setAcctList(List<BankAccount> acctList) {
		this.acctList = acctList;
	}

	@Override
	public String toString() {
		return "Vendor [name=" + name + ", email=" + email + ", password=" + password + ", regAmount=" + regAmount
				+ ", regDate=" + regDate + ", acctList=" + acctList + "]";
	}

	

	
	
}
