package org.drip.services.impl;

import org.apache.commons.lang.StringUtils;
import org.drip.DripUtils;
import org.drip.controller.WebUser;
import org.drip.model.Customer;
import org.drip.model.User;
import org.drip.repository.CustomerRepository;
import org.drip.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
    public Customer getCustomer(String firstName, String lastName, String accountNumber, String areaCode, String phoneNumber, String zipCode) {
		return customerRepository.findCustomer(firstName, lastName, accountNumber, areaCode, phoneNumber, zipCode);
	}
	
	@Override
    public Customer getCustomer(String businessName, String accountNumber,String areaCode, String phoneNumber, String zipCode) {
		return customerRepository.findCustomer(businessName, accountNumber, areaCode, phoneNumber, zipCode);
	}
	
	@Override
    public Customer getCustomer(String email) {
		return customerRepository.findByEmail(email);
	}

	@Override
    public Customer saveCustomer(Customer customer) {
	    return customerRepository.save(customer);
    }

	@Override
    public Customer saveCustomer(WebUser webUser) {
		Customer customer = null;
		if (!StringUtils.isBlank(webUser.getFirstName()) && !StringUtils.isBlank(webUser.getLastName())) {
			customer = getCustomer(webUser.getFirstName(), webUser.getLastName(), webUser.getAccountNumber(), webUser.getAreaCode(), webUser.getPhoneNumber(), webUser.getZipCode());
		}
		if (!StringUtils.isBlank(webUser.getBusinessName())) {
			customer = getCustomer(webUser.getBusinessName(), webUser.getAccountNumber(), webUser.getAreaCode(), webUser.getPhoneNumber(), webUser.getZipCode());
		}
		customer.setEmail(webUser.getEmail());
		String encodedPassword = DripUtils.encryptPassword(webUser.getPassword());
		User user = new User(webUser.getEmail(),encodedPassword);
		customer.setUser(user);
		customer.setRegistered(true);
	    return customerRepository.save(customer);
    }	
}
