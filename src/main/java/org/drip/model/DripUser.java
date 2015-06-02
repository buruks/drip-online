package org.drip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="drip_user")
public class DripUser {
	@Id
	private Long id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="business_name")
	private String businessName;
	@Column(name="account_number")
	private int accountNumber;
	@Column(name="phone_number")
	private String phoneNumber;
	@Column(name="area_code")
	private String areaCode;
	@Column(name="zip_code")
	private String zipCode;
	
    public Long getId() {
    	return id;
    }

    public void setId(Long id) {
    	this.id = id;
    }

	public String getFirstName() {
    	return firstName;
    }
	
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }
	
    public String getLastName() {
    	return lastName;
    }
	
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
	
    public String getBusinessName() {
    	return businessName;
    }
	
    public void setBusinessName(String businessName) {
    	this.businessName = businessName;
    }
	
    public int getAccountNumber() {
    	return accountNumber;
    }
	
    public void setAccountNumber(int accountNumber) {
    	this.accountNumber = accountNumber;
    }
	
    public String getPhoneNumber() {
    	return phoneNumber;
    }
	
    public void setPhoneNumber(String phoneNumber) {
    	this.phoneNumber = phoneNumber;
    }
	
    public String getZipCode() {
    	return zipCode;
    }
	
    public void setZipCode(String zipCode) {
    	this.zipCode = zipCode;
    }
	
    public Boolean isRegistered() {
    	return registered;
    }
	
    public void setRegistered(Boolean registered) {
    	this.registered = registered;
    }
	private Boolean registered; 
}
