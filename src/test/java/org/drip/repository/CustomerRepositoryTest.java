package org.drip.repository;

import javax.transaction.Transactional;

import junit.framework.Assert;

import org.drip.config.DataSourceConfig;
import org.drip.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@ContextConfiguration(classes = { DataSourceConfig.class })
@DatabaseSetup("classpath:testData.xml")
public class CustomerRepositoryTest {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	@Transactional
	public void testFindByFirstLastName() {
		Customer customer = customerRepository.findCustomer("John", "Doe", "123456", "12345", "12345678", "123");
		Assert.assertEquals("John", customer.getFirstName());
		Assert.assertEquals("Doe", customer.getLastName());
		Assert.assertEquals("12345678", customer.getPhoneNumber());
		Assert.assertTrue(!customer.getAccountNumbers().isEmpty());
	}
	
	@Test
	@Transactional
	public void testFindByBusinessName() {
		Customer customer = customerRepository.findCustomer("Business", "123457", "12345", "123456789", "1234");
		Assert.assertEquals("Business", customer.getBusinessName());
		Assert.assertEquals("123456789", customer.getPhoneNumber());
		Assert.assertTrue(!customer.getAccountNumbers().isEmpty());
	}
	
	@Test
	@Transactional
	public void testFindByEmail() {
		Customer customer = customerRepository.findByEmail("business@test.com");
		Assert.assertEquals("Business", customer.getBusinessName());
		Assert.assertEquals("123456789", customer.getPhoneNumber());
		Assert.assertTrue(!customer.getAccountNumbers().isEmpty());
	}
	
}
