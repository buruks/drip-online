package org.drip.controller;

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
	
    public String getZipCode() {
    	return zipCode;
    }
	
    public void setZipCode(String zipCode) {
    	this.zipCode = zipCode;
    }
	
    public String getAreaCode() {
    	return areaCode;
    }
	
    public void setAreaCode(String areaCode) {
    	this.areaCode = areaCode;
    }
	
    public String getPhoneNumber() {
    	return phoneNumber;
    }
	
    public void setPhoneNumber(String phoneNumber) {
    	this.phoneNumber = phoneNumber;
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
    
	private String firstName;
	private String lastName;
	private String businessName;
	private String accountNumber;
	
	private String zipCode;
	private String areaCode;
	private String phoneNumber;
	
	private String email;
	private String password;	
	private String confirmPassword;
	
	
}
