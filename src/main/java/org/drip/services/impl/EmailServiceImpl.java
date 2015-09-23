package org.drip.services.impl;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.drip.model.Customer;
import org.drip.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {
	Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	@Override
	public Boolean sendMail(String message, Customer customer) {
		
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setAuthentication("drip.reset@gmail.com", "r3s3tP@55w0rd");
		email.setStartTLSEnabled(true);
		Boolean isSent;
		
		try {
			URL url = new URL("http://localhost:9000/assets/img/logo/nwcwd_sm_clr.gif");
			String cid = email.embed(url, "NWCWD logo");
			String finalMessage = "<html><div><p><strong>The following message just came in from:</strong></p>"
					+ "<div style=\"margin-left:50px;margin-right:50px;background: #EFFBFB none repeat scroll 0% 0% !important; color: black;padding:5px;\">"
					+ "<br />Customer Name: " + customer.getFullName()
					+ "<br />Phone Number: " + customer.getPhoneNumber()
					+ "<br />ZIP Code: " + customer.getZipCode()
					+ "<br />Phone Number: " + customer.getAreaCode() + " - " + customer.getPhoneNumber()
					+ "<br /><br /> " + 
					message + "<p></p></div>"
					+ "<br /><img src=\"cid:"+cid+"\" height=\"120\" width=\"120\" style=\"margin-left:50px;\"></div></html>";
			// embed the image and get the content id
			
	        email.setFrom("drip.reset@gmail.com","Drip Online");
	        email.setSubject("DRIP ONLINE: Customer Enquiry");
			email.setMsg(message);
			email.addTo(customer.getEmail(), customer.getFullName());
			// set the html message
			email.setHtmlMsg(finalMessage);
			// set the alternative message
			email.setTextMsg(message);
			email.send();
			
			isSent = true;
        }
        catch (EmailException|MalformedURLException e) {
        	isSent = false;
        	e.printStackTrace();
        	logger.error("there was an error sending mail: "+ e.getMessage());
        }
		
		return isSent;
	}
	
}
