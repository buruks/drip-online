package org.drip.controller;

import org.drip.model.Customer;
import org.drip.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	Customer customer = (Customer) (SecurityContextHolder.getContext().getAuthentication());
	
	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public String getAccounts(Model model) {
		model.addAttribute("accounts", customer.getId());
		return "accounts";
	}
	
	@RequestMapping(value = "/payments", method = RequestMethod.GET)
	public String getAccountPayments(@RequestParam("accountId") String accountId, Model model) {
		model.addAttribute("payments", accountService.getPayments(accountId));
		return "payments";
	}
	
}
