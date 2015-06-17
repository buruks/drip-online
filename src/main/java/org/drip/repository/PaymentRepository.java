package org.drip.repository;

import java.util.List;

import org.drip.model.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
	
	@Query("SELECT payment FROM Payment payment INNER JOIN payment.account INNER JOIN payment.paymentType WHERE payment.account.accountNumber=:accountNumber ")
	List<Payment> findByAccountNumber(@Param("accountNumber") String accountId);

}
