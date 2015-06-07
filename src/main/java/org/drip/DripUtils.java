package org.drip;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DripUtils {
	
	public static String encryptPassword(String password) {
		BCryptPasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
		return passwordEncorder.encode(password);
	}
}
