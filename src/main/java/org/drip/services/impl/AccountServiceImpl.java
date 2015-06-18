package org.drip.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drip.model.Account;
import org.drip.model.BillSummary;
import org.drip.model.Payment;
import org.drip.repository.AccountRepository;
import org.drip.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;
		
	@Override
	public List<Account> getAccounts(Long customerId) {
		
		List<Account> account = accountRepository.findByCustomerId(customerId);
		return account;
	}
	
	@Override
	public List<Payment> getPayments(String accountNumber) {
		List<Payment> payments = accountRepository.findPaymentsByAccountNumber(accountNumber);
		return payments;
	}
	
	@Override
    public List<BillSummary> getBillSummaries(String accountNumber) {		
	    return accountRepository.findBillSummaries(accountNumber);
    }

	@Override
    public Map<String, List<BillSummary>> getBillSummaries(Long customerId) {
		Map<String, List<BillSummary>> billSummariesMap = new HashMap<String, List<BillSummary>>();
		List<BillSummary> billSummaries = accountRepository.findBillSummaries(customerId);
		for (BillSummary billSummary : billSummaries) {
			String accountNumber = billSummary.getAccount().getAccountNumber();
			if (billSummariesMap.containsKey(accountNumber)) {
				billSummariesMap.get(accountNumber).add(billSummary);
			} else {
				List<BillSummary> bills = new ArrayList<BillSummary>();
				bills.add(billSummary);
				billSummariesMap.put(accountNumber, bills);
			}
		}
		return billSummariesMap;
    }

	@Override
    public Map<String, List<Payment>> getPaymentsByCustomer(Long customerId) {
		Map<String, List<Payment>> paymentMap = new HashMap<String, List<Payment>>();
		List<Payment> payments = accountRepository.findPaymentsByCustomerId(customerId);
		for(Payment payment: payments)	{
			String accountNumber = payment.getAccount().getAccountNumber();
			if (paymentMap.containsKey(accountNumber)) {
				paymentMap.get(accountNumber).add(payment);
			} else {
				List<Payment> pay = new ArrayList<Payment>();
				pay.add(payment);
				paymentMap.put(accountNumber, pay);
			}
		}
	    return paymentMap;
    }
	
}
