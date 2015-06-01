package org.drip.services;

import org.drip.model.User;
import org.drip.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUser(String firstName, String lastName, String phoneNumber, String zipCode) {
		return userRepository.findByFirstNameAndLastNameAndPhoneNumberAndZipCode(firstName, lastName, phoneNumber, zipCode);
	}
	
	public User getUser(String businessName, String phoneNumber, String zipCode) {
		return userRepository.findByBusinessNameAndPhoneNumberAndZipCode(businessName, phoneNumber, zipCode);
	}
	
	public User saveUser(User user) {
		encryptPassword(user);
		return userRepository.save(user);
	}
	
	private void encryptPassword(User user) {
		BCryptPasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncorder.encode(user.getPassword()));
	}
	
}
