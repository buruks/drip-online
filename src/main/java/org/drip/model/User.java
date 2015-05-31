package org.drip.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;
	private String businessName;
	
	private String zipCode;
	private String phoneNumber;
	private String email;
	
	private String username;
	private String password;
	
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
	
    public String getZipCode() {
    	return zipCode;
    }
	
    public void setZipCode(String zipCode) {
    	this.zipCode = zipCode;
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
	
    public String getUsername() {
    	return username;
    }
	
    public void setUsername(String username) {
    	this.username = username;
    }
	
    public String getPassword() {
    	return password;
    }
	
    public void setPassword(String password) {
    	this.password = password;
    }
}
