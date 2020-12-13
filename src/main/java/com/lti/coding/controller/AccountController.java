package com.lti.coding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lti.coding.entity.Account;
import com.lti.coding.service.AccountService;
import com.lti.coding.service.CustomerService;

public class AccountController {
	@Autowired
	public AccountService accountService;

	@PostMapping("/account/add")
	public String createAccount(@RequestParam Account account) {
		return this.accountService.createAccount(account);
	}

	@PostMapping("/account/update")
	public String updateAccount(@RequestParam Account account) {
		return this.accountService.updateAccount(account);
	}

	@PostMapping("/account/remove/{id}")
	public String removeAccount(@PathVariable int id) {
		return this.accountService.removeAccount(id);
	}
	
	@PostMapping("/account/transfer")
	public List<Account> transferAccount(@RequestParam int fromId, int toId,double amount) {
		return this.accountService.transferAmount(fromId, toId, amount);
	}
}