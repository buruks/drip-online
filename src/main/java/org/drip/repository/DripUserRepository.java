package org.drip.repository;

import org.drip.model.DripUser;
import org.springframework.data.repository.CrudRepository;


public interface DripUserRepository extends CrudRepository<DripUser, Long> {
	DripUser findByFirstNameAndLastNameAndAccountNumberAndAreaCodeAndPhoneNumberAndZipCode(String firstName, String lastName, int accountNumber, String areaCode,String phoneNumber, String zipCode);	
	DripUser findByBusinessNameAndAccountNumberAndAreaCodeAndPhoneNumberAndZipCode(String businessName, int accountNumber, String areaCode, String phoneNumber, String zipCode);
}
