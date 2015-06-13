package org.drip.services;

import org.drip.model.Account;
import org.drip.model.Payment;

public interface AccountService {

	Account getAccount(int customerId);
	Payment getPayment(String accountId);

}
