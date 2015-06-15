package org.drip.repository;

import org.drip.model.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
	
	@Query("SELECT paymentHistory FROM PaymentHistory paymentHistory INNER JOIN paymentHistory.account INNER JOIN paymentHistory.paymentType WHERE account.accountNumber=:accountNumber ")
	Payment findByAccountNumber(@Param("accountNumber") String accountId);
}
