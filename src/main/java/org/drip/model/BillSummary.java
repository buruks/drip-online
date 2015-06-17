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
	private Long id;
	
	@Column(name = "bill_date")
	@Temporal(TemporalType.DATE)
	private Date billDate;
	
	@Column(name = "amount_due")
	private Double amountDue;
	
	@Column(name = "current_amount")
	private Double currentAmount;
	
	@Column(name = "amount_paid")
	private Double amountPaid;
	
	private Boolean paid;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getBillDate() {
		return billDate;
	}
	
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	
	public Double getAmountDue() {
		return amountDue;
	}
	
	public void setAmountDue(Double amountDue) {
		this.amountDue = amountDue;
	}
	
	public Double getCurrentAmount() {
		return currentAmount;
	}
	
	public void setCurrentAmount(Double currentAmount) {
		this.currentAmount = currentAmount;
	}
	
	public Double getAmountPaid() {
		return amountPaid;
	}
	
	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
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
