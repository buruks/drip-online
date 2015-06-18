package org.drip.services;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.drip.model.Account;
import org.drip.model.BillSummary;
import org.drip.model.Payment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class AccountServiceTest extends AbstractServiceTest {
	
	@Autowired
	AccountService accountService;
	
	@Test
	public void testGetBillSummariesForGivenAccount() {		
		String accountNumber = "123456";
		List<BillSummary> billSummaries = accountService.getBillSummaries(accountNumber);
		assertEquals(2, billSummaries.size());
	}
	
	@Test
	public void testGetBillSummariesForClient() {
		Long clientId = Long.valueOf(2);
		Map<String, List<BillSummary>> billSummariesMap = accountService.getBillSummaries(clientId);
		assertEquals(2, billSummariesMap.size());
	}
	
	@Test
	public void testGetBillSummariesForClientWithNoBills() {		
		String accountNumber = "XXXXXX";
		List<BillSummary> billSummaries = accountService.getBillSummaries(accountNumber);
		assertEquals(0, billSummaries.size());
	}
	
	@Test
	public void testGetAccounts() {
		Long customerId = 2L;
		List<Account> accounts = accountService.getAccounts(customerId);
		assertEquals(2, accounts.size());
	}
	
	@Test
	public void testGetPaymentByAccount() {
		String accountNumber = "123456";
		List<Payment> payments = accountService.getPayments(accountNumber);
		assertEquals(2,payments.size());
	}
	
	@Test
	public void testGetPaymentByMissingAccount() {
		String accountNumber = "FFFFFFF";
		List<Payment> payments = accountService.getPayments(accountNumber);
		assertEquals(0,payments.size());
	}
	
	@Test
	public void testGetPaymentsByCustomerId() {
		Long customerId = 2L;
		Map<String, List<Payment>> payments = accountService.getPaymentsByCustomer(customerId);
		assertEquals(2,payments.size());
	}
}
