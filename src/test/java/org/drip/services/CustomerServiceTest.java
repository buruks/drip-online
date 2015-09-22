package org.drip.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import javax.transaction.Transactional;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.drip.AbstractDripTest;
import org.drip.controller.WebUser;
import org.drip.exceptions.CustomerAlreadyRegisteredException;
import org.drip.model.Customer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiceTest extends AbstractDripTest {
	
	@Autowired
	private CustomerService customerService;
	
	@Test
	public void testFindCustomerByEmailReturnsCustomer() {
		Customer customer = customerService.getCustomer("business@test.com");
		Assert.assertEquals("Business", customer.getBusinessName());
		Assert.assertEquals("123456789", customer.getPhoneNumber());
		Assert.assertEquals("business@test.com", customer.getUser().getUsername());
	}
	
	@Test
	public void testFindCustomerByWrongEmailReturnsNull() {
		Customer customer = customerService.getCustomer("wrong@test.org");
		Assert.assertNull(customer);
	}
	
	@Test
	public void testFindCustomerByAccountNumberUsingNonExistantAccount() {
		Customer customer1 = customerService.getCustomerByAccountNumber("YYYY");
		Assert.assertNull(customer1);
	}
	
	@Test
	@Transactional
	public void testRegiteringUnregisteredCustomer() {
		String accountNumber = "123456";
		Customer customerToRegister = customerService.getCustomerByAccountNumber(accountNumber);
		assertEquals(false, customerToRegister.isRegistered());
		WebUser webUser = new WebUser();
		webUser.setAccountNumber(accountNumber);
		webUser.setFirstName(customerToRegister.getFirstName());
		webUser.setLastName(customerToRegister.getLastName());
		webUser.setEmail("john.doe@testmail.com");
		webUser.setState("test STATE");
		webUser.setCity("TEST city");
		webUser.setStreet("TEST STREET");
		webUser.setZipCode("12345");
		webUser.setPassword("secret");
		
		Customer registeredCustomer = customerService.registerCustomer(webUser);
		
		assertEquals(true, registeredCustomer.isRegistered());
		assertEquals("john.doe@testmail.com", registeredCustomer.getUser().getUsername());
		assertFalse(StringUtils.equalsIgnoreCase(webUser.getPassword(), registeredCustomer.getUser().getPassword()));
	}
	
	@Test(expected= CustomerAlreadyRegisteredException.class) 
	@Transactional
	public void testRegisterRegisteredCustomer() {
		Customer alreadyRegisteredCustomer =  customerService.getCustomerByAccountNumber("456789"); 
		String accountNumber = "456789";
		WebUser webUser = new WebUser();
		webUser.setAccountNumber(accountNumber);
		webUser.setBusinessName(alreadyRegisteredCustomer.getBusinessName());
		webUser.setEmail(alreadyRegisteredCustomer.getEmail());
		webUser.setFirstName(alreadyRegisteredCustomer.getFirstName());
		webUser.setLastName(alreadyRegisteredCustomer.getLastName());
		webUser.setZipCode(alreadyRegisteredCustomer.getZipCode());
		Customer customer = customerService.registerCustomer(webUser);
		assertEquals(false, customer.isRegistered());
	}

	@Test
	@Transactional
	public void testRegisteringCustomerWithWrongPremiseAddress() {
		String accountNumber = "123456";
		Customer customerToRegister = customerService.getCustomerByAccountNumber(accountNumber);
		assertEquals(false, customerToRegister.isRegistered());
		WebUser webUser = new WebUser();
		webUser.setAccountNumber(accountNumber);
		webUser.setFirstName(customerToRegister.getFirstName());
		webUser.setLastName(customerToRegister.getLastName());
		webUser.setEmail("john.doe@testmail.com");
		webUser.setState("WRONG STATE");
		webUser.setCity("TEST CITY");
		webUser.setStreet("WRONG STREET");
		webUser.setZipCode("12345");
		webUser.setPassword("secret");
		Customer registeredCustomer = customerService.registerCustomer(webUser);
		assertEquals(false, registeredCustomer.isRegistered());
		assertNull(registeredCustomer.getUser());
	}
	
	@Test
	public void testFindCustomerByAccountNumber() {
		Customer customer = customerService.getCustomerByAccountNumber("123456");
		assertEquals("John", customer.getFirstName());
		assertEquals("Doe", customer.getLastName());
	}
}
