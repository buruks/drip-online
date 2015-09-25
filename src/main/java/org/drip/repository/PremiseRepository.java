package org.drip.repository;

import org.drip.model.Premise;
import org.springframework.data.repository.Repository;

public interface PremiseRepository extends Repository<Premise, Long>{
	
	Premise findByAccountsAccountNumber(String accountNumber);

}
