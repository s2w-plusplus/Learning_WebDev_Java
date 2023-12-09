package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.*;


@Entity
@Table(name="bk_acct")
public class BankAccount extends BaseEntity {

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private AccountType accType;
	private double balance= 0.0;
	@Column(name = "tx_date")
	private LocalDate transactionDate;

	@ManyToOne
	@JoinColumn(name = "User_Id")
	private User acctOwner;
	
	
	public BankAccount() {
		System.out.println("in BankAccount ctor");
	}
	
	
	public BankAccount(AccountType accType, double balance, LocalDate transactionDate) {
		super();
		this.accType = accType;
		this.balance = balance;
		this.transactionDate = transactionDate;
	}


	public AccountType getAccType() {
		return accType;
	}
	public void setAccType(AccountType accType) {
		this.accType = accType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	public User getAcctOwner() {
		return acctOwner;
	}
	public void setAcctOwner(User acctOwner) {
		this.acctOwner = acctOwner;
	}


	@Override
	public String toString() {
		return "BankAccount [accType=" + accType + ", balance=" + balance + ", transactionDate=" + transactionDate
				+ ", acctOwner=" + acctOwner + "]";
	}


}
