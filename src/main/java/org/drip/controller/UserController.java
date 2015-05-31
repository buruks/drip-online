package org.drip.controller;

import org.drip.model.User;
import org.drip.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class UserController {
	
	UserService userService = new UserService();
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerUser(Model model) {
		return "register";
	}
	
	@RequestMapping(value = "/confirmUser", method = RequestMethod.POST)
	public String confirmUserDetails(@RequestParam("firstName") String firstName,
	                                 @RequestParam("lastName") String lastName,
	                                 @RequestParam("businessName") String businessName,
	                                 @RequestParam("phoneNumber") String phoneNumber,
	                                 @RequestParam("zipCode") String zipCode, Model model) {
		User user;
		if (businessName != null && !StringUtils.isEmpty(businessName)) {
			user = userService.getUser(businessName, phoneNumber, zipCode);
		} else {
			user = userService.getUser(firstName, lastName, phoneNumber, zipCode);
		}
		if (user == null) {
			model.addAttribute("error", "User with provided details cannot be found. Please review details and try again.");
		} else {
			model.addAttribute("user", user);
		}
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user, Model model, SessionStatus status) {
		userService.saveUser(user);
		model.addAttribute("success", "User details saved!");
		status.setComplete();
		return "login";
	}
}
