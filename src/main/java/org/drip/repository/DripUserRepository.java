package org.drip.repository;

import org.drip.model.DripUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface DripUserRepository extends CrudRepository<DripUser, Long> {
	
	
	@Query("SELECT dripUser FROM DripUser dripUser inner join dripUser.accountNumbers accNumbers WHERE dripUser.firstName =:firstName and dripUser.lastName =:lastName and dripUser.phoneNumber =:phoneNumber and dripUser.zipCode =:zipCode and dripUser.areaCode =:areaCode and accNumbers.accountNumber =:accountNumber")
	public DripUser findDripUser(@Param("firstName")  String firstName, @Param("lastName") String lastName, @Param("accountNumber") String accountNumber, @Param("phoneNumber") String phoneNumber, @Param("zipCode") String zipCode, @Param("areaCode") String areaCode);
	
	@Query("SELECT dripUser FROM DripUser dripUser inner join dripUser.accountNumbers accNumbers WHERE dripUser.businessName =:businessName and dripUser.phoneNumber =:phoneNumber and dripUser.zipCode =:zipCode and dripUser.areaCode =:areaCode and accNumbers.accountNumber =:accountNumber")
	public DripUser findDripUser(@Param("businessName") String businessName, @Param("accountNumber") String accountNumber, @Param("phoneNumber") String phoneNumber, @Param("zipCode") String zipCode, @Param("areaCode") String areaCode);
}
