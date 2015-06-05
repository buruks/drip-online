package org.drip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="account_numbers")
public class AccountNumber {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="account_number")
	private String accountNumber;
	
	@ManyToOne
	private DripUser dripUser;
	
	public int getId()
	{
		return id;
	}	
	public void setId(int id)
	{
		this.id=id;
	}
	
	public String getAccountNumber()
	{
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber)
	{
		this.accountNumber=accountNumber;
	}
	
	public DripUser getDripUser()
	{
		return dripUser;
	}
	
	public void setDripUser(DripUser dripUser)
	{
		this.dripUser=dripUser;
	}
}
