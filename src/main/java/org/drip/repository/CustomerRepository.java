package org.drip.repository;

import org.drip.model.Customer;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	Customer findByEmail(String email);

	Customer findByAccountsAccountNumber(String accountNumber);
	
	Customer findByUserResetHashHash(String hash);
}
