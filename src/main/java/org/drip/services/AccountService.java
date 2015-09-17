package org.drip.services;

import java.util.List;
import java.util.Map;

import org.drip.model.Account;
import org.drip.model.BillSummary;
import org.drip.model.Payment;
import org.drip.model.Usage;

public interface AccountService {
	
	List<Account> getAccounts(Long customerId);
	
	List<Payment> getPayments(String accountId);
	
	Map<String, List<Payment>> getPaymentsByCustomer(Long customerId);
	
	List<BillSummary> getBillSummaries(String accountNumber);
	
	Map<String, List<BillSummary>> getBillSummaries(Long customerId);
	
	List<Usage> getUsagesByAccount(String accountNumber);
	
	Map<String, List<Usage>> getUsagesByCustomer(Long customerId);
	
}
