package org.drip.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "bill_summary")
public class BillSummary {
	
	@Id
	private int id;
	@Column(name = "bill_date")
	@Temporal(TemporalType.DATE)
	private Date billDate;
	private Double amount;
	private Boolean paid;
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
    public int getId() {
    	return id;
    }
	
    public void setId(int id) {
    	this.id = id;
    }
	
    public Date getBillDate() {
    	return billDate;
    }
	
    public void setBillDate(Date billDate) {
    	this.billDate = billDate;
    }
	
    public Double getAmount() {
    	return amount;
    }
	
    public void setAmount(Double amount) {
    	this.amount = amount;
    }
	
    public Boolean getPaid() {
    	return paid;
    }
	
    public void setPaid(Boolean paid) {
    	this.paid = paid;
    }
	
    public Account getAccount() {
    	return account;
    }
	
    public void setAccount(Account account) {
    	this.account = account;
    }
	
}
