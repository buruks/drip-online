package org.drip.repository;

import java.util.List;

import org.drip.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> findByFirstNameAndLastNameAndPhonNumberAndZipCode(String firstName, String lastName, String phoneNumber, String zipCode);
	
	List<User> findByBusinessNameAndPhoneNumberAndZipCode(String businessName, String phoneNumber, String zipCode);
	
}
