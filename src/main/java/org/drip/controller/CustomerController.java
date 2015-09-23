package org.drip.controller;

import org.drip.exceptions.CustomerAlreadyRegisteredException;
import org.apache.commons.lang.StringUtils;
import org.drip.model.Customer;
import org.drip.services.CustomerService;
import org.drip.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	@Qualifier("webUserValidator")
	Validator validator;
	
	@RequestMapping(value = "/")
	public String root() {
		return "redirect:/accounts";
	}
	
	@RequestMapping(value = "/index")
	public String index() {
		return "redirect:/accounts";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerUser(Model model) {
		model.addAttribute("user", new WebUser());
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") WebUser webUser, BindingResult result, Model model,
	                       RedirectAttributes redirectAttributes) {
		validator.validate(webUser, result);
		if (result.hasErrors()) {
			return "register";
		} else {
			try {
				Customer customer = customerService.registerCustomer(webUser);
				Authentication auth = new UsernamePasswordAuthenticationToken(customer, null,
				        AuthorityUtils.createAuthorityList("USER"));
				
				SecurityContextHolder.getContext().setAuthentication(auth);
				redirectAttributes.addFlashAttribute("success", "saved.success");
				return "redirect:/accounts";
			}
			catch (CustomerAlreadyRegisteredException ex) {
				result.addError(new ObjectError("webUser", ex.getMessage()));
				return "register";
			}
			
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error() {
		return "error";
	}
	
	@RequestMapping(value = "/contact-us", method = RequestMethod.GET)
	public String displayContactUsPage(Model model) {
		Boolean isAuthenticated = !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
		if (isAuthenticated) {
			Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("customer", customer);
			return "contact-us";
		} else {
			return "redirect:/login";
		}
		
	}
	
	@RequestMapping(value="/contact-us",method=RequestMethod.POST)
	public String submitContactUsMessage(@RequestParam("message") String message, Model model) {
		if (StringUtils.isBlank(message)) {
			model.addAttribute("messageRequired", "message.required");
			return "contact-us";
		}
		Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("customer", customer);
		if (emailService.sendMail(message, customer)) {
			return "redirect:/accounts";
		} else {
			model.addAttribute("errorSaving", "error.saving");
			return "contact-us";
		}
	}
	
	
}
