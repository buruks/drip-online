package org.drip.services;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.drip.model.BillSummary;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class AccountServiceTest extends AbstractServiceTest {
	
	@Autowired
	AccountService accountService;
	
	@Test
	public void testGetBillSummaries() {		
		String accountNumber = "123456";
		List<BillSummary> billSummaries = accountService.getBillSummaries(accountNumber);
		assertEquals(2, billSummaries.size());
	}
	
	@Test
	public void testGetBillSummariesForClientWithNoBills() {		
		String accountNumber = "XXXXXX";
		List<BillSummary> billSummaries = accountService.getBillSummaries(accountNumber);
		assertEquals(0, billSummaries.size());
	}
	
}
