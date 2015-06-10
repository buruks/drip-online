package org.drip.services;

import org.drip.controller.WebUser;
import org.drip.model.Customer;

public interface CustomerService {
	
	Customer getCustomer(String firstName, String lastName, String accountNumber, String areaCode, String phoneNumber,
	                     String zipCode);
	
	Customer getCustomer(String businessName, String accountNumber, String areaCode, String phoneNumber, String zipCode);
	
	Customer getCustomer(String email);
	
	Customer saveCustomer(Customer customer);
	
	Customer saveCustomer(WebUser webUser);	
}
