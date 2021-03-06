package org.drip.controller;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.drip.ValidationUtils;
import org.drip.model.User;
import org.drip.services.PasswordService;
import org.drip.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value="/password")
public class PasswordController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PasswordService passwordService;
	
	@Autowired
	CustomerService userService;
	
	@Autowired
	MessageSource messageSource;
		
	@RequestMapping(value="/forgot", method=RequestMethod.GET)
	public String get(Model model) {
		model.addAttribute("email", "");
		return "forgot_password";
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public String sendLink(HttpServletRequest request, @ModelAttribute("email") String email, BindingResult result,
	                       Model model, RedirectAttributes redirectAttributes) {
		ValidationUtils.validateEmailString(email, result);		
		if (!result.hasErrors()) {
			ValidationUtils.validateEmailExists(email, userService, result);
		}
		if (result.hasErrors()) {
			return "forgot_password";
		} else {
			String hash = generateHash();
			passwordService.saveHash(email, hash);
			String resetUrl = request.getScheme() + "://" + request.getServerName() 
					+ ":" + request.getServerPort() + "/password/reset?hash=" + hash;			
			try {
				passwordService.sendResetLink(email, resetUrl);
				redirectAttributes.addFlashAttribute("sent", messageSource.getMessage("message.sent", new Object[] { email }, null));
				return "redirect:/login";
			}
			catch (MessagingException ex) {
				log.error("Error while setting up mail.\n" + ex);
				result.reject("setup.error");
				return "forgot_password";
			}
			catch (MailException ex) {
				log.error("Error while sending reset password.\n" + ex);
				result.reject("mailing.error");
				return "forgot_password";
			}
		}
	}
	
	@RequestMapping(value="/reset",method=RequestMethod.GET)
	public String getReset(@RequestParam(value = "hash", required = false) String hash, Model model) {
		//Boolean isAuthenticated = !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
		User user = passwordService.getUserAssociatedWithHash(hash);
		if (user != null) {
			model.addAttribute("username", user.getUsername());
			return "reset_forgot";
		} else {
			return "redirect:/index";
		}
	}
	
	@RequestMapping(value="/reset",method=RequestMethod.POST)
	public String reset(@RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword, @RequestParam("username") String username, BindingResult result, Model model) {
		if (StringUtils.isBlank(password)) {
			model.addAttribute("passwordRequired", "password.required");
			return "reset_forgot";
		}
		if (!StringUtils.equals(password, confirmPassword)) {
			model.addAttribute("passwordMatch", "password.match");
			return "reset_forgot";
		}
		
		ValidationUtils.validatePassword(password, result);
		if(result.hasErrors()) {
			model.addAttribute("passwordMatch", "password.invalid");
			return "reset_forgot";
		}
		
		if (passwordService.updatePassword(username, password)) {
			return "redirect:/login";
		} else {
			model.addAttribute("errorSaving", "error.saving");
			return "reset_forgot";
		}
	}
	
	private String generateHash() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(260, random).toString(32);
	}
}
