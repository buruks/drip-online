package org.drip.controller;

import org.drip.model.Premise;

public class WebUser {
	
	
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
	
    public String getAccountNumber() {
    	return accountNumber;
    }
	
    public void setAccountNumber(String accountNumber) {
    	this.accountNumber = accountNumber;
    }
	
    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
    	return zipCode;
    }
	
    public void setZipCode(String zipCode) {
    	this.zipCode = zipCode;
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
	
    public String getConfirmPassword() {
    	return confirmPassword;
    }
	
    public void setConfirmPassword(String confirmPassword) {
    	this.confirmPassword = confirmPassword;
    }
    

	public Premise getPremise() {
		Premise premise = new Premise();
		
		premise.setState(this.state);
		premise.setCity(this.city);
		premise.setStreet(this.street);
		premise.setZipCode(this.zipCode);
		
		return premise;
	}
	
	private String firstName;
	private String lastName;
	private String businessName;
	private String accountNumber;
	
	private String state;
	private String city;
	private String street;
	private String zipCode;
	
	private String email;
	private String password;	
	private String confirmPassword;
	
}
