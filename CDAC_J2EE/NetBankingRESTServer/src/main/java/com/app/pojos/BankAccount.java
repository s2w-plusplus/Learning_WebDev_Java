package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bank_accounts")
@Getter
@Setter
@NoArgsConstructor
public class BankAccount extends BaseEntity{
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private AcctType acctType;
	private double balance;
	@ManyToOne(fetch = FetchType.LAZY)
	
	@JoinColumn(name="cust_id")
	private Customer acctOwner;
	public BankAccount(AcctType acctType, double balance) {
		super();
		this.acctType = acctType;
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "BankAccount [acctType=" + acctType + ", balance=" + balance + "]";
	}
	
	
		
}
