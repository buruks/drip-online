package org.drip.services;

import junit.framework.Assert;

import org.drip.model.Customer;
import org.drip.model.User;
import org.drip.repository.CustomerRepository;
import org.drip.services.impl.CustomerServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CustomerServiceTest {
	
	@Configuration
	static class CustomerServiceTestContextConfiguration {
		
		@Bean
		public CustomerService customerService() {
			return new CustomerServiceImpl();
		}
		
		@Bean
		public CustomerRepository customerRepository() {
			return Mockito.mock(CustomerRepository.class);
		}
		
	}
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Before
	public void setup() {
		User user1 = new User("john@test.com", "xxxx");
		String firstName = "John";
		String lastName = "Doe";
		String businessName = "business";
		String areaCode = "123452";
		String phoneNumber = "123456789";
		String accountNumber = "12345678";
		String zipCode = "123";
		
		Customer customerWithFirstLastName = new Customer();		
		customerWithFirstLastName.setFirstName(firstName);		
		customerWithFirstLastName.setLastName(lastName);		
		customerWithFirstLastName.setPhoneNumber(phoneNumber);		
		customerWithFirstLastName.setAreaCode(areaCode);
		customerWithFirstLastName.setZipCode(zipCode);
		customerWithFirstLastName.setUser(user1);		
		Mockito.when(customerRepository.findCustomer(firstName, lastName, accountNumber, areaCode, phoneNumber, zipCode)).thenReturn(customerWithFirstLastName);
		
		User user2 = new User("business@test.org", "yyyy");
		Customer customerWithBusinessName = new Customer();
		customerWithBusinessName.setBusinessName(businessName);
		customerWithBusinessName.setPhoneNumber(phoneNumber);
		customerWithBusinessName.setAreaCode(areaCode);
		customerWithBusinessName.setZipCode(zipCode);
		customerWithBusinessName.setUser(user2);				
		Mockito.when(customerRepository.findByEmail("business@test.org")).thenReturn(customerWithBusinessName);
		Mockito.when(customerRepository.findCustomer(businessName, accountNumber, areaCode, phoneNumber, zipCode)).thenReturn(customerWithBusinessName);
	}
	
	@Test
	public void testFindCustomerByEmailReturnsCustomer() {
		Customer customer = customerService.getCustomer("business@test.org");
		Assert.assertEquals("business", customer.getBusinessName());
		Assert.assertEquals("123456789", customer.getPhoneNumber());
		Assert.assertEquals("business@test.org", customer.getUser().getUsername());
		Mockito.verify(customerRepository, VerificationModeFactory.times(1)).findByEmail(Mockito.anyString());
	}
	
	@Test 
	public void testFindCustomerByFirstLastNameAndOtherDetailsReturnsCustomer() {
		Customer customer = customerService.getCustomer("John", "Doe", "12345678", "123452", "123456789", "123");
		Assert.assertEquals("John", customer.getFirstName());
		Assert.assertEquals("Doe", customer.getLastName());
		Assert.assertEquals("123456789", customer.getPhoneNumber());
		Assert.assertEquals("john@test.com", customer.getUser().getUsername());
		Mockito.verify(customerRepository, VerificationModeFactory.times(1)).findCustomer(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
	}
	
	@Test
	public void testFindCustomerByBusinessNameAndOtherDetailsReturnsCustomer() {
		Customer customer = customerService.getCustomer("business", "12345678", "123452", "123456789", "123");
		Assert.assertEquals("business", customer.getBusinessName());
		Assert.assertEquals("123456789", customer.getPhoneNumber());
		Assert.assertEquals("business@test.org", customer.getUser().getUsername());
		Mockito.verify(customerRepository, VerificationModeFactory.times(1)).findCustomer(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
	}
	
	@Test
	public void testFindCustomerByWrongEmailReturnsNull() {
		Customer customer = customerService.getCustomer("wrong@test.org");
		Assert.assertNull(customer);
		Mockito.verify(customerRepository, VerificationModeFactory.times(1)).findByEmail(Mockito.anyString());
	}
	
	@Test
	public void testFindCustomerByNonExistentDetailsReturnsNull() {
		Customer customer1 = customerService.getCustomer("John", "YYYY", "12345678", "123452", "12345678", "12345");
		Customer customer2 = customerService.getCustomer("business2", "12345678", "123452", "12345678", "12345");
		Assert.assertNull(customer1);
		Assert.assertNull(customer2);
		Mockito.verify(customerRepository, VerificationModeFactory.times(1)).findCustomer(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
		Mockito.verify(customerRepository, VerificationModeFactory.times(1)).findCustomer(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
	}
	
	@After
	public void reset() {		
		Mockito.reset(customerRepository);
	}
}
