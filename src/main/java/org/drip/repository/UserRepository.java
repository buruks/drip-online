package org.drip.repository;

import org.drip.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByFirstNameAndLastNameAndPhoneNumberAndZipCode(String firstName, String lastName, String phoneNumber, String zipCode);
	
	User findByBusinessNameAndPhoneNumberAndZipCode(String businessName, String phoneNumber, String zipCode);
	
	User findByUsername(String username);
	
}
