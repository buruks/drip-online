package org.drip.services;

import java.util.List;

import org.drip.model.Account;
import org.drip.model.BillSummary;
import org.drip.model.Payment;

public interface AccountService {
	
	Account getAccount(int customerId);
	
	Payment getPayment(String accountId);

	List<BillSummary> getBillSummaries(String accountNumber);
	
}
