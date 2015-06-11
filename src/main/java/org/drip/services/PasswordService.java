package org.drip.services;

import javax.mail.MessagingException;

import org.drip.model.Customer;
import org.drip.model.ResetHash;
import org.drip.model.User;
import org.springframework.mail.MailException;


public interface PasswordService {
	void sendResetLink(String email, String resetUrl) throws MessagingException, MailException;
	User getUserAssociatedWithHash(String hash);
	Boolean updatePassword(String username, String newPassword);
	ResetHash saveHash(String email, String hash);
}
