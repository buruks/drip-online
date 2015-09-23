package org.drip.exceptions;

public class CustomerAlreadyRegisteredException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public CustomerAlreadyRegisteredException(String accountNumber) {
		super("Customer with account number " + accountNumber + " is already registered");
		this.accountNumber = accountNumber;
	}
	
	private String accountNumber;
	
	public String getAccountNumber() {
		return accountNumber;
	}
}
