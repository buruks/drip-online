package org.drip;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.drip.model.Customer;
import org.drip.services.CustomerService;
import org.springframework.validation.Errors;

public class ValidationUtils {
	
	public static void validateEmailString(String email, Errors errors) {
		String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			errors.reject("email.invalid");
		}
	}
	
	public static void validateEmailExists(String email, CustomerService service, Errors errors) {
		Customer customer = service.getCustomer(email);
		if (customer == null) {
			errors.reject("email.match.error");
		}
	}
	
	public static void validatePassword(String password, Errors errors) {
		Pattern pattern = null;
		Matcher matcher;
		final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
		pattern = Pattern.compile(PASSWORD_PATTERN);
		matcher = pattern.matcher(password);
		if (!matcher.matches()) {
			errors.reject("password.invalid");
		}
	}
}
