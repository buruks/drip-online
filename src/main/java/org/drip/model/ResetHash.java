package org.drip.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="reset_hash", indexes = {
		@Index(columnList="hash", name="hash_idx")
})
public class ResetHash {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reset_hash_id")
	private Long id;
	@Column(unique=true)
	private String hash;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expire_date")
	private Date expireDate;
	private Boolean used;
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="user_id")
	private User user;
	
    public Long getId() {
    	return id;
    }
	
    public void setId(Long id) {
    	this.id = id;
    }
	
    public String getHash() {
    	return hash;
    }
	
    public void setHash(String hash) {
    	this.hash = hash;
    }
	
    public Date getExpireDate() {
    	return expireDate;
    }
	
    public void setExpireDate(Date expireDate) {
    	this.expireDate = expireDate;
    }
    
    public Boolean getUsed() {
    	return this.used;
    }
    
    public void setUsed(Boolean used) {
    	this.used = used;
    }
	
    public User getUser() {
    	return user;
    }
	
    public void setUser(User user) {
    	this.user = user;
    }
    
    public ResetHash() {
    }
    
    public ResetHash(User user, String hash) {
    	this.user = user;
    	this.hash = hash;
    }
}
