package org.drip.repository;

import java.util.List;

import org.drip.model.Account;
import org.drip.model.BillSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends CrudRepository<Account, Long> {
	
	@Query("SELECT account FROM Account account WHERE account.customer.id =:id")
	Account findByCustomerId(@Param("id") int customerId);
	
	@Query("SELECT billSummary FROM BillSummary billSummary WHERE billSummary.account.accountNumber =:accountNumber")
	List<BillSummary> findBillSummaries(@Param("accountNumber") String accountNumber);
	
}
