package org.drip.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import javax.transaction.Transactional;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.drip.controller.WebUser;
import org.drip.model.Customer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiceTest extends AbstractServiceTest {
	
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
	public void testFindCustomerByFirstLastNameAndOtherDetailsReturnsCustomer() {
		Customer customer = customerService.getCustomer("John", "Doe", "123456", "12345", "12345678", "123");
		Assert.assertEquals("John", customer.getFirstName());
		Assert.assertEquals("Doe", customer.getLastName());
		Assert.assertEquals("12345678", customer.getPhoneNumber());
	}
	
	@Test
	public void testFindCustomerByBusinessNameAndOtherDetailsReturnsCustomer() {
		Customer customer = customerService.getCustomer("Business", "123457", "12345", "123456789", "1234");
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
	public void testFindCustomerByNonExistentDetailsReturnsNull() {
		Customer customer1 = customerService.getCustomer("John", "YYYY", "12345678", "123452", "12345678", "12345");
		Customer customer2 = customerService.getCustomer("business2", "12345678", "123452", "12345678", "12345");
		Assert.assertNull(customer1);
		Assert.assertNull(customer2);
	}
	
	@Test
	@Transactional
	public void testRegiterUnregisteredCustomer () {
		Customer customerToRegister = customerService.getCustomer("John", "Doe", "123456", "12345", "12345678", "123");
		assertEquals(false, customerToRegister.isRegistered());
		WebUser webUser = new WebUser();
		webUser.setAccountNumber(customerToRegister.getAccounts().get(0).getAccountNumber());
		webUser.setFirstName(customerToRegister.getFirstName());
		webUser.setLastName(customerToRegister.getLastName());
		webUser.setEmail("john.doe@testmail.com");
		webUser.setAreaCode(customerToRegister.getAreaCode());
		webUser.setPhoneNumber(customerToRegister.getPhoneNumber());
		webUser.setZipCode(customerToRegister.getZipCode());
		webUser.setPassword("secret");
		Customer registeredCustomer = customerService.registerCustomer(webUser);
		assertEquals(true, registeredCustomer.isRegistered());
		assertEquals("john.doe@testmail.com", registeredCustomer.getUser().getUsername());
		assertFalse(StringUtils.equalsIgnoreCase(webUser.getPassword(), registeredCustomer.getUser().getPassword()));
	}
}
