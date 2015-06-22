package org.drip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="authorities")
public class Role implements GrantedAuthority {
	
    private static final long serialVersionUID = -2259326409617683357L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
	
    private String authority;

	@Override
    public String getAuthority() {
	    return authority;
    }
	
    public void setAuthority(String authority) {
    	this.authority = authority;
    }
	
}
