package org.drip.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="payment_type")
public class PaymentType {
	@Id 
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
	
	@Column(name="payment_type_code")
	private String paymentTypeCode;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="paymentType")
	public List<Payment> payment;
	
	public int getId() {
		return id;
	}	
	public void setId(int id) {
		this.id=id;
	}
	
	public String getPaymentTypeCode() {
		return paymentTypeCode;
	}	
	public void setPaymentTypeCode(String paymentTypeCode) {
		this.paymentTypeCode=paymentTypeCode;
	}
	
	public String getDescription() {
		return description;
	}	
	public void setPaymentTypeDescription(String description) {
		this.description=description;
	}	
	public List<Payment> getPayment() {
		return payment;
	}	
	
}
