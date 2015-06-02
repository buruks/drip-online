package org.drip;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.drip.model.DripUser;
import org.drip.model.User;
import org.drip.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
	
	@Autowired
	private UserService userService; 
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "email", "email.required");
		ValidationUtils.rejectIfEmpty(errors, "areaCode", "areacode.required");
		ValidationUtils.rejectIfEmpty(errors, "phoneNumber", "phonenumber.required");
		ValidationUtils.rejectIfEmpty(errors, "zipCode", "zipcode.required");
		ValidationUtils.rejectIfEmpty(errors, "password", "password.required");
		ValidationUtils.rejectIfEmpty(errors, "accountNumber", "Account Number Required");
		User user = (User)obj;
		if (!StringUtils.isBlank(user.getEmail()) && !isValidEmailAddress(user.getEmail())) {
			errors.rejectValue("email", "email.invalid");
		}
		if (!StringUtils.equals(user.getPassword(),user.getConfirmPassword())) {
			errors.rejectValue("password", "password.match");
		}
		if (StringUtils.isBlank(user.getFirstName()) && StringUtils.isBlank(user.getLastName()) && StringUtils.isBlank(user.getBusinessName())) {
			errors.reject("name.required");
		}
		if (!StringUtils.isBlank(user.getFirstName()) && !StringUtils.isBlank(user.getLastName())) {
			DripUser dripUser = userService.getUser(user.getFirstName(), user.getLastName(),  user.getAccountNumber(), user.getAreaCode(), user.getPhoneNumber(), user.getZipCode());
			validateUserWithDripInfo(errors, dripUser);
		}
		if (!StringUtils.isBlank(user.getBusinessName())) {
			DripUser dripUser = userService.getUser(user.getBusinessName(),  user.getAccountNumber(), user.getAreaCode(), user.getPhoneNumber(), user.getZipCode());
			validateUserWithDripInfo(errors, dripUser);
		}
		if (!StringUtils.isBlank(user.getEmail())) {
			User onlineUser = userService.getUser(user.getEmail());
			if (onlineUser != null) {
				errors.rejectValue("email", "email.unique");
			}
		}
		//check if account name is empty
	}

	
    public void setUserService(UserService userService) {
    	this.userService = userService;
    }

	private void validateUserWithDripInfo(Errors errors, DripUser dripUser) {
	    if (dripUser == null) {
	    	errors.reject("registration.details.invalid");
	    } else if (dripUser.isRegistered()) {
	    	errors.reject("registration.already.registered");
	    }
    }
	
	private Boolean isValidEmailAddress(String email) {
		String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
}
