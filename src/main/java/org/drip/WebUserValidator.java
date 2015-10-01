package org.drip;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.drip.controller.WebUser;
import org.drip.model.Customer;
import org.drip.model.Premise;
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
		ValidationUtils.rejectIfEmpty(errors, "state", "state.required");
		ValidationUtils.rejectIfEmpty(errors, "city", "city.required");
		ValidationUtils.rejectIfEmpty(errors, "street", "street.required");
		ValidationUtils.rejectIfEmpty(errors, "zipCode", "zipcode.required");
		ValidationUtils.rejectIfEmpty(errors, "password", "password.required");
		ValidationUtils.rejectIfEmpty(errors, "accountNumber", "accountnumber.required");
		WebUser user = (WebUser) obj;
		if (!StringUtils.isBlank(user.getEmail()) && !isValidEmailAddress(user.getEmail())) {
			errors.rejectValue("email", "email.invalid");
		}
		if (!StringUtils.equals(user.getPassword(), user.getConfirmPassword()) && !isValidPassword(user.getPassword())) {
			errors.rejectValue("password", "password.match");
		}
		if (StringUtils.isBlank(user.getFirstName()) && StringUtils.isBlank(user.getLastName())
		        && StringUtils.isBlank(user.getBusinessName())) {
			errors.reject("name.required");
		}
		
		if (!StringUtils.isBlank(user.getEmail())) {
			Customer registeredCustomer = customerService.getCustomer(user.getEmail());
			if (registeredCustomer != null && registeredCustomer.isRegistered()) {
				errors.rejectValue("email", "email.unique");
			}
		}
		
		Customer customer = customerService.getCustomerByAccountNumber(user.getAccountNumber());
		if (customer == null) {
			errors.reject("registration.details.invalid");
		} else if (customer.isRegistered()) {
			errors.reject("registration.already.registered");
		} else {			
			validatePremiseInfo(errors, user);			
		}
	}
	
	public void setUserService(CustomerService userService) {
		this.customerService = userService;
	}
	
	private void validatePremiseInfo(Errors errors, WebUser user) {
		Premise premise = customerService.getCurrentPremise(user.getAccountNumber());
		if (premise == null || !premise.addressMatches(user.getPremise())){			
			errors.reject("registration.details.invalid.address");
		}
	}
	
	private Boolean isValidEmailAddress(String email) {
		String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	private Boolean isValidPassword(String password) {
		Pattern pattern = null;
		Matcher matcher;
		final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
		pattern = Pattern.compile(PASSWORD_PATTERN);
		matcher = pattern.matcher(password);
		return matcher.matches();
	}
	
}
