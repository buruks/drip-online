package org.drip.repository;

import org.drip.model.Account;
import org.drip.model.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends CrudRepository<Account, Long> {
	@Query("SELECT account FROM Account account WHERE account.dripUser.id =:id")
	Account findByCustomerId(@Param("id") int customerId);
	
}
