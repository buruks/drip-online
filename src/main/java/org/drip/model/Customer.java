package org.drip.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "business_name")
	private String businessName;
	
	@Column(unique = true)
	private String email;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "area_code")
	private String areaCode;
	
	@Column(name = "zip_code")
	private String zipCode;
	
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "customer")
	private List<AccountNumber> accountNumbers;
	
	private Boolean registered;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="user_id")
	private User user;
	
	public Customer() {
	}
	
	public Customer(Customer customer) {
		this.accountNumbers = customer.getAccountNumbers();
		this.areaCode = customer.getAreaCode();
		this.businessName = customer.getBusinessName();
		this.email = customer.getEmail();
		this.firstName = customer.getFirstName();
		this.id = customer.getId();
		this.lastName = customer.getLastName();
		this.phoneNumber = customer.getPhoneNumber();
		this.registered = customer.isRegistered();
		this.user = customer.getUser();
		this.zipCode = customer.getZipCode();
    }

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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAreaCode() {
		return areaCode;
	}
	
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	public List<AccountNumber> getAccountNumbers() {
		return accountNumbers;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getFullName() {
    	return firstName + " " + lastName;
    }
}
