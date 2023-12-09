package com.app.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="User_table")
public class User extends BaseEntity {
	@Column(name="Name",length = 30)
	private String name;
	@Column(name="Email",length = 30,unique = true)
	private String email;
	@Column(name="Password",length = 20,nullable = false)
	private String password;
	@Column(name="Opening_Date")
	private LocalDate acctOpeningDate;
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "acctOwner",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<BankAccount> accounts=new ArrayList<>();
	
	public User() {
		System.out.println("in User ctor");
	}
	

	public User(String name, String email, String password, LocalDate acctOpeningDate, Role role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.acctOpeningDate = acctOpeningDate;
		this.role = role;
	}





	//convenience / helper method : add / remove
		public void addAccount(BankAccount acct)
		{
			//User ---> BankAccount
			accounts.add(acct);
			//BankAccount ---> User
			acct.setAcctOwner(this);
			
		}
		public void removeAccount(BankAccount acct)
		{
			//User --X-> BankAccount
			accounts.remove(acct);
			//BankAccount --X--> User
			acct.setAcctOwner(null);
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
		public LocalDate getAcctOpeningDate() {
			return acctOpeningDate;
		}
		public void setAcctOpeningDate(LocalDate acctOpeningDate) {
			this.acctOpeningDate = acctOpeningDate;
		}
		public Role getRole() {
			return role;
		}
		public void setRole(Role role) {
			this.role = role;
		}
		public List<BankAccount> getAccounts() {
			return accounts;
		}
		public void setAccounts(List<BankAccount> accounts) {
			this.accounts = accounts;
		}


		@Override
		public String toString() {
			return "User [name=" + name + ", email=" + email + ", password=" + password + ", acctOpeningDate="
					+ acctOpeningDate + ", role=" + role + "]";
		}


		
		
		
		
}
