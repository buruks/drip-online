package org.drip.services;

import org.drip.controller.WebUser;
import org.drip.model.Customer;
import org.drip.model.Premise;

public interface CustomerService {
	
	Customer getCustomer(String email);
	
	Customer saveCustomer(Customer customer);
	
	Customer registerCustomer(WebUser webUser);

	Premise getCurrentPremise(String accountNumber);

	Customer getCustomerByAccountNumber(String accountNumber);
}
