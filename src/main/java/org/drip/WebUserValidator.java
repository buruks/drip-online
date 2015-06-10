package org.drip;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.drip.controller.WebUser;
import org.drip.model.Customer;
import org.drip.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class WebUserValidator implements Validator {
	
	@Autowired
	private CustomerService customerService; 
	
	@Override
	public boolean supports(Class<?> clazz) {
		return WebUser.class.equals(clazz);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "email", "email.required");
		ValidationUtils.rejectIfEmpty(errors, "areaCode", "areacode.required");
		ValidationUtils.rejectIfEmpty(errors, "phoneNumber", "phonenumber.required");
		ValidationUtils.rejectIfEmpty(errors, "zipCode", "zipcode.required");
		ValidationUtils.rejectIfEmpty(errors, "password", "password.required");
		ValidationUtils.rejectIfEmpty(errors, "accountNumber", "accountnumber.required");
		WebUser user = (WebUser)obj;
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
			Customer customer = customerService.getCustomer(user.getFirstName(), user.getLastName(), user.getAccountNumber(), user.getAreaCode(), user.getPhoneNumber(), user.getZipCode());
			validateCustomerInfo(errors, customer);
		}
		if (!StringUtils.isBlank(user.getBusinessName())) {
			Customer customer = customerService.getCustomer(user.getBusinessName(), user.getAccountNumber(), user.getAreaCode(), user.getPhoneNumber(), user.getZipCode());
			validateCustomerInfo(errors, customer);
		}
		if (!StringUtils.isBlank(user.getEmail())) {
			Customer registeredCustomer = customerService.getCustomer(user.getEmail());
			if (registeredCustomer != null) {
				errors.rejectValue("email", "email.unique");
			}
		}
	}
	
    public void setUserService(CustomerService userService) {
    	this.customerService = userService;
    }

	private void validateCustomerInfo(Errors errors, Customer customer) {
	    if (customer == null) {
	    	errors.reject("registration.details.invalid");
	    } else if (customer.isRegistered()) {
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
