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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private long id;
	
	@Column(unique=true)
	private String username;
	private String password;

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private List<Role> roles;
	
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="user_id")
	private List<ResetHash> resetHash;
	
	@OneToOne(mappedBy = "user")
	private Customer customer;
	
	public User() {	
	}    

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public long getId() {
    	return id;
    }

	
    public void setId(long id) {
    	this.id = id;
    } 
	
    public String getUsername() {
    	return username;
    }
  
    public void setUsername(String email) {
    	this.username = email;
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
    
    public List<ResetHash> getResetHash() {
    	return resetHash;
    }
    
    public void setCustomer(Customer customer) {
    	this.customer = customer;
    }
}
