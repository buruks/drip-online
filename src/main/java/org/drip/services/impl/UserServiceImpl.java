package org.drip.services.impl;

import org.apache.commons.lang.StringUtils;
import org.drip.model.DripUser;
import org.drip.model.User;
import org.drip.repository.DripUserRepository;
import org.drip.repository.UserRepository;
import org.drip.services.PasswordService;
import org.drip.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DripUserRepository dripUserRepository;
	
	@Autowired
	private PasswordService passwordService;
	/* (non-Javadoc)
	 * @see org.drip.services.IUserService#getUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
    public DripUser getUser(String firstName, String lastName, String areaCode, String phoneNumber, String zipCode) {
		return dripUserRepository.findByFirstNameAndLastNameAndAreaCodeAndPhoneNumberAndZipCode(firstName, lastName, areaCode, phoneNumber, zipCode);
	}
	
	/* (non-Javadoc)
	 * @see org.drip.services.IUserService#getUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
    public DripUser getUser(String businessName, String areaCode, String phoneNumber, String zipCode) {
		return dripUserRepository.findByBusinessNameAndAreaCodeAndPhoneNumberAndZipCode(businessName, areaCode, phoneNumber, zipCode);
	}
	
	/* (non-Javadoc)
	 * @see org.drip.services.IUserService#getUser(java.lang.String)
	 */
	@Override
    public User getUser(String email) {
		return userRepository.findByEmail(email);
	}
	
	/* (non-Javadoc)
	 * @see org.drip.services.IUserService#saveUser(org.drip.model.User)
	 */
	@Override
    public User registerUser(User user) {
		user.setPassword(passwordService.encryptPassword(user.getPassword()));
		updateDripUser(user);
		return userRepository.save(user);
	}

	private void updateDripUser(User user) {
	    DripUser dripUser;
	    if (!StringUtils.isBlank(user.getFirstName()) && !StringUtils.isBlank(user.getLastName())) {
	    	dripUser = dripUserRepository.findByFirstNameAndLastNameAndAreaCodeAndPhoneNumberAndZipCode(user.getFirstName(), user.getLastName(), user.getAreaCode(), user.getPhoneNumber(), user.getZipCode());
	    } else {
	    	dripUser = dripUserRepository.findByBusinessNameAndAreaCodeAndPhoneNumberAndZipCode(user.getBusinessName(), user.getAreaCode(), user.getPhoneNumber(), user.getZipCode());
	    }
	    if (dripUser != null) {
	    	dripUser.setRegistered(true);
	    	dripUserRepository.save(dripUser);
	    }
    }

	@Override
    public User saveUser(User user) {
	    return userRepository.save(user);
    }	
}
