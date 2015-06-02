package org.drip.services;

import org.drip.model.DripUser;
import org.drip.model.User;

public interface UserService {
	
	public abstract DripUser getUser(String firstName, String lastName, int accountNumber,String areaCode, String phoneNumber, String zipCode);
	
	public abstract DripUser getUser(String businessName, int accountNumber,String areaCode, String phoneNumber, String zipCode);
	
	public abstract User getUser(String email);
	
	public abstract User saveUser(User user);
	
}
