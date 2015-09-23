package org.drip.repository;

import java.util.List;

import org.drip.model.Account;
import org.drip.model.BillSummary;
import org.drip.model.Payment;
import org.drip.model.Usage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends Repository<Account, Long> {
	
	@Query("SELECT account FROM Account account WHERE account.customer.id =:id")
	List<Account> findByCustomerId(@Param("id") Long customerId);
	
	@Query("SELECT payment FROM Payment payment INNER JOIN payment.account INNER JOIN payment.paymentType WHERE payment.account.accountNumber=:accountNumber ")
	List<Payment> findPaymentsByAccountNumber(@Param("accountNumber") String accountNumber);
	
	@Query("SELECT payment FROM Payment payment WHERE payment.account.customer.id=:customerId")
	List<Payment> findPaymentsByCustomerId(@Param("customerId") Long customerId);
	
	@Query("SELECT billSummary FROM BillSummary billSummary WHERE billSummary.account.accountNumber =:accountNumber")
	List<BillSummary> findBillSummaries(@Param("accountNumber") String accountNumber);
	
	@Query("SELECT billSummary FROM BillSummary billSummary WHERE 	billSummary.account.customer.id =:customerId")
	List<BillSummary> findBillSummaries(@Param("customerId") Long customerId);
	
	@Query("SELECT usage FROM Usage usage WHERE usage.account.accountNumber=:accountNumber")
	List<Usage> findUsageByAccount(@Param("accountNumber") String accountNumber);
	
	@Query("SELECT usage FROM Usage usage WHERE usage.account.customer.id=:customerId")
	List<Usage> findUsageByCustomerId(@Param("customerId") Long customerId);
	
}
