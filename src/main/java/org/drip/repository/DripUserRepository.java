package org.drip.repository;

import org.drip.model.DripUser;
import org.springframework.data.repository.CrudRepository;


public interface DripUserRepository extends CrudRepository<DripUser, Long> {
	DripUser findByFirstNameAndLastNameAndAreaCodeAndPhoneNumberAndZipCode(String firstName, String lastName, String areaCode,String phoneNumber, String zipCode);	
	DripUser findByBusinessNameAndAreaCodeAndPhoneNumberAndZipCode(String businessName, String areaCode, String phoneNumber, String zipCode);
}
