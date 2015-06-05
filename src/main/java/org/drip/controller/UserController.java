package org.drip.controller;

import org.drip.model.User;
import org.drip.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	@Qualifier("userValidator")
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
		model.addAttribute("user", new User());
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user, BindingResult result, Model model) {
		validator.validate(user, result);
		if (result.hasErrors()) {
			return "register";
		} else {
			userService.registerUser(user);
			model.addAttribute("success", "User details saved!");
			return "redirect:login";
		}		
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
}
