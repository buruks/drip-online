package org.drip.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="premise")
public class Premise {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="premise_id")
	private Long id;

	
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "premise")
	private List<Account> accounts;

	@Column(name="premise_number")
	private String premiseNumber;

	private String city;

	private String state;

	private String street;

	@Column(name="zip_code")
	private String zipCode;
	
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	
	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
	
	public String getState() { return state; }
	public void setState(String state) { this.state = state; }
	
	public String getStreet() { return street; }
	public void setStreet(String street) { this.street = street; }
	
	public String getZipCode() { return zipCode; }
	public void setZipCode(String zipCode) { this.zipCode = zipCode; }

	public Boolean addressMatches(Premise premise) {
		return this.getCity().equalsIgnoreCase(premise.getCity()) && 
				this.getState().equalsIgnoreCase(premise.getState()) && 
				this.getStreet().equalsIgnoreCase(premise.getStreet()) &&
				this.getZipCode().equalsIgnoreCase(premise.getZipCode());
	}

}
