package org.drip.services.impl;

import org.drip.DripUtils;
import org.drip.controller.WebUser;
import org.drip.exceptions.CustomerAlreadyRegisteredException;
import org.drip.model.Customer;
import org.drip.model.Premise;
import org.drip.model.User;
import org.drip.repository.CustomerRepository;
import org.drip.repository.PremiseRepository;
import org.drip.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PremiseRepository premiseRepository; 
	
	@Override
	public Customer getCustomer(String email) {
		return customerRepository.findByEmail(email);
	}
	
	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	@Override
	public Customer registerCustomer(WebUser webUser) {
		Customer customer = customerRepository.findByAccountsAccountNumber(webUser.getAccountNumber());
		if (customer.isRegistered()) {
			throw new CustomerAlreadyRegisteredException(webUser.getAccountNumber());
		}
		Premise premise = premiseRepository.findByAccountsAccountNumber(webUser.getAccountNumber());
		if (premise.addressMatches(webUser.getPremise())) {
			customer.setEmail(webUser.getEmail());
			String encodedPassword = DripUtils.encryptPassword(webUser.getPassword());
			User user = new User(webUser.getEmail(),encodedPassword);
			customer.setUser(user);
			customer.setRegistered(true);
			return customerRepository.save(customer);
		}
		return customer;
	}

	@Override
	public Premise getCurrentPremise(String accountNumber) {
		return premiseRepository.findByAccountsAccountNumber(accountNumber);
	}

	@Override
	public Customer getCustomerByAccountNumber(String accountNumber) {
		return customerRepository.findByAccountsAccountNumber(accountNumber);
	}
}
