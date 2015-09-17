package org.drip.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name="is_active")
	private Boolean isActive;
	
	@Column(name="last_billed")
	private Date lastBilled;
	
	@Column(name="current_balance")
	private Double currentBalance;
	
	@Column(name="water_state")
	private Boolean waterState; 
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "account")
	private List<Payment> payments;
	
	@OneToMany(fetch = FetchType.LAZY,  cascade = CascadeType.ALL, mappedBy= "account")
	public List<Usage> usage;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}
	
	public void setLastBilled(Date lastBilled) {
		this.lastBilled = lastBilled;
	}
	
	public Date getLastBilled() {
		return lastBilled;
	}
	
	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}
	
	public Double getCurrentBalance() {
		return currentBalance;
	}
	
	public void setWaterState(Boolean waterState) {
		this.waterState = waterState;
	}
	
	public Boolean getWaterState() {
		return waterState;
	}	
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public List<Payment> getPayments() {
		return payments;
	}
	
	public List<Usage> getUsage() {
		return usage;
	}
}
