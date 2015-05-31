package org.drip.services;

import org.drip.model.User;
import org.drip.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUser(String firstName, String lastName, String phoneNumber, String zipCode) {
		return userRepository.findByFirstNameAndLastNameAndPhonNumberAndZipCode(firstName, lastName, phoneNumber, zipCode).get(0);
	}
	
	public User getUser(String businessName, String phoneNumber, String zipCode) {
		return userRepository.findByBusinessNameAndPhoneNumberAndZipCode(businessName, phoneNumber, zipCode).get(0);
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
}
