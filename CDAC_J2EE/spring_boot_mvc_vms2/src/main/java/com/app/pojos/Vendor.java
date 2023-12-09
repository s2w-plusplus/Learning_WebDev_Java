package com.app.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@PrimaryKeyJoinColumn(name = "vendor_id")
public class Vendor extends User {
	@Column(name = "reg_amt")
	@Min(500)
	@Max(5000)
	private double regAmount;
	@Column(name = "reg_date")
	@NotNull(message = "reg date is required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate regDate;
	@OneToMany(mappedBy = "acctOwner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BankAccount> accounts = new ArrayList<>();
	// uni dir association between entity n value type(embeddable)
	@Embedded // optional : added only for understanding
	private PaymentMode mode;
	public Vendor() {
		System.out.println("in vendor ctor");
	}
	
	public Vendor(@Min(500) @Max(5000) double regAmount, @NotNull(message = "reg date is required") LocalDate regDate,
			PaymentMode mode) {
		super();
		this.regAmount = regAmount;
		this.regDate = regDate;
		this.mode = mode;
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
	public List<BankAccount> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<BankAccount> accounts) {
		this.accounts = accounts;
	}
	public PaymentMode getMode() {
		return mode;
	}
	public void setMode(PaymentMode mode) {
		this.mode = mode;
	}
	@Override
	public String toString() {
		return "Vendor "+super.toString()+" [regAmount=" + regAmount + ", regDate=" + regDate  + ", mode=" + mode
				+ "]";
	}
	
}
