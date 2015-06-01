package org.drip.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="business_name")
	private String businessName;
	
	@Column(name="zip_code")
	private String zipCode;
	@Column(name="phone_number")
	private String phoneNumber;
	private String email;
	
	private String username;
	private String password;
	
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private List<Role> roles;
	
	public User() {	
	}    

	public User(User user) {
		this.id = user.id;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.businessName = user.businessName;
		this.email = user.email;
		this.username = user.username;
		this.password = user.password;
		this.roles = user.roles;
	}
	
	public long getId() {
    	return id;
    }

	
    public void setId(long id) {
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

    public List<Role> getRoles() {
    	return roles;
    }

	
    public void setRoles(List<Role> authorities) {
    	this.roles = authorities;
    }
}
