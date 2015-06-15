package org.drip.repository;

import org.drip.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	
	@Query("SELECT customer FROM Customer customer inner join customer.accountNumbers accNumbers WHERE customer.firstName =:firstName and customer.lastName =:lastName and customer.phoneNumber =:phoneNumber and customer.zipCode =:zipCode and customer.areaCode =:areaCode and accNumbers.accountNumber =:accountNumber")
	public Customer findCustomer(@Param("firstName")  String firstName, @Param("lastName") String lastName, @Param("accountNumber") String accountNumber, @Param("areaCode") String areaCode, @Param("phoneNumber") String phoneNumber, @Param("zipCode") String zipCode);
	
	@Query("SELECT customer FROM Customer customer inner join customer.accountNumbers accNumbers WHERE customer.businessName =:businessName and customer.phoneNumber =:phoneNumber and customer.zipCode =:zipCode and customer.areaCode =:areaCode and accNumbers.accountNumber =:accountNumber")
	public Customer findCustomer(@Param("businessName") String businessName, @Param("accountNumber") String accountNumber, @Param("areaCode") String areaCode, @Param("phoneNumber") String phoneNumber, @Param("zipCode") String zipCode);
	
	Customer findByEmail(String email);
}
