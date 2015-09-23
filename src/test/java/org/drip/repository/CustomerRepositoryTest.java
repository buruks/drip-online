package org.drip.repository;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.drip.AbstractDripTest;
import org.drip.model.Customer;
import org.drip.model.ResetHash;
import org.drip.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import junit.framework.Assert;

public class CustomerRepositoryTest extends AbstractDripTest {
	
	@Autowired
	CustomerRepository customerRepository;

	@Test
	public void testSavingResetHash() {
		ResetHash resetHash = new ResetHash();
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		resetHash.setExpireDate(calendar.getTime());
		String hash = "RRR-RRRRR-RRRRR";
		resetHash.setHash(hash);
		resetHash.setUsed(false);		
		Customer customer = customerRepository.findByEmail("business@test.com");
		User user = customer.getUser();
		user.setResetHash(resetHash);
		
		Customer savedCustomer = customerRepository.save(customer);
		
		Assert.assertEquals(hash, savedCustomer.getUser().getResetHash().getHash());
	}
	
	@Test
	public void testUpdatingResetHash() {
		String hash = "c70826f21f3d4db4a7b3e867613d144c";		
		Customer customer = customerRepository.findByEmail("jane.doe@test.com");
		User user = customer.getUser();
		ResetHash resetHash = user.getResetHash();
		Assert.assertEquals(hash, resetHash.getHash());
		Assert.assertTrue(resetHash.getUsed());
		
		String newHash = "RRR-RRRRR-RRRRRR";
		resetHash.setHash(newHash);
		Date expireDate = DateUtils.addDays(new Date(), 1);
		resetHash.setExpireDate(expireDate);
		resetHash.setUsed(false);
		
		ResetHash savedResetHash = customerRepository.save(customer).getUser().getResetHash();
		
		Assert.assertEquals(newHash, savedResetHash.getHash());
		Assert.assertFalse(savedResetHash.getUsed());
		
	}
	
	@Test
	public void testFindingCustomerByHash() {
		Customer customer = customerRepository.findByUserResetHashHash("c70826f21f3d4db4a7b3e867613d144c");
		
		Assert.assertEquals("jane.doe@test.com", customer.getEmail());
		Assert.assertTrue(customer.getUser().getResetHash().getUsed());
	}

}
