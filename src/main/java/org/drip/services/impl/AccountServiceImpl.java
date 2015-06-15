package org.drip.services.impl;

import java.util.List;

import org.drip.model.Account;
import org.drip.model.BillSummary;
import org.drip.model.Payment;
import org.drip.repository.AccountRepository;
import org.drip.repository.PaymentRepository;
import org.drip.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Override
	public Account getAccount(int customerId) {
		Account account = accountRepository.findByCustomerId(customerId);
		return account;
	}
	
	@Override
	public Payment getPayment(String accountId) {
		// TODO Auto-generated method stub
		Payment payment = paymentRepository.findByAccountNumber(accountId);
		return payment;
	}

	@Override
    public List<BillSummary> getBillSummaries(String accountNumber) {
	    return accountRepository.findBillSummaries(accountNumber);
    }
	
}
