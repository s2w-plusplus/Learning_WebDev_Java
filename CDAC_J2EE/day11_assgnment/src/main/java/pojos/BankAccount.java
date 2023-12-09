package pojos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "BnkAcct")
public class BankAccount extends BaseEntity {

	@Enumerated(EnumType.STRING) // by default uses ordinal
	@Column(name = "Account_Type",length = 20)
	private AccountType acctype;
	
	@Column(name = "Balance")
	private double balance;
	
	@Temporal(TemporalType.DATE) // by default uses timestamp
	@Column(name = "Last_TxDate")
	private Date lastTxDate;
	
	//bi-directional association
	//many side, child side ,owning side of association 
	@ManyToOne()
	@JoinColumn(name = "Vendor_ID")
	private Vendor acctOwner;

	
	public BankAccount() {
		System.out.println("Inside default constructor of: "+getClass().getName());
	}

	public BankAccount(AccountType acctype, double balance, Date lastTxDate) {
		super();
		this.acctype = acctype;
		this.balance = balance;
		this.lastTxDate = lastTxDate;
	}
	
	
	public AccountType getAcctype() {
		return acctype;
	}
	public void setAcctype(AccountType acctype) {
		this.acctype = acctype;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Date getLastTxDate() {
		return lastTxDate;
	}
	public void setLastTxDate(Date lastTxDate) {
		this.lastTxDate = lastTxDate;
	}
	public Vendor getAcctOwner() {
		return acctOwner;
	}
	public void setAcctOwner(Vendor acctOwner) {
		this.acctOwner = acctOwner;
	}

	@Override
	public String toString() {
		return "BankAccount [acctype=" + acctype + ", balance=" + balance + ", lastTxDate=" + lastTxDate + "]";
	}

	
	
	
	
	
}

