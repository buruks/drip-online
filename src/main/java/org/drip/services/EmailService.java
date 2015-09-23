package org.drip.services;

import org.drip.model.Customer;


public interface EmailService {
	Boolean sendMail(String message, Customer customer);
}
