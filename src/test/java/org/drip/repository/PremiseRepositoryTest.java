package org.drip.repository;

import static org.junit.Assert.assertEquals;

import org.drip.model.Premise;
import org.drip.AbstractDripTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PremiseRepositoryTest extends AbstractDripTest {

	@Autowired
	private PremiseRepository pRepository;
	
	@Test
	public void testFindPremiseByAccountNumber() {
		Premise premise = pRepository.findByAccountsAccountNumber("123456");
		assertEquals("TEST CITY", premise.getCity());
		assertEquals("TEST STATE", premise.getState());
		assertEquals("TEST STREET", premise.getStreet());
		assertEquals("12345", premise.getZipCode());
	}

}
