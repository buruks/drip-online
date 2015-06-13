package org.drip.controller;

import org.drip.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	@Qualifier("webUserValidator")
	Validator validator;
	
	@RequestMapping(value="/")
	public String root() {
		return "redirect:index";
	}
	
	@RequestMapping(value="/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerUser(Model model) {
		model.addAttribute("user", new WebUser());
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") WebUser webUser, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		validator.validate(webUser, result);
		if (result.hasErrors()) {
			return "register";
		} else {
			customerService.registerCustomer(webUser);
			redirectAttributes.addAttribute("success", "saved.success");
			return "redirect:login";
		}		
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
}
