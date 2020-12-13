package com.lti.coding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.coding.entity.Account;
import com.lti.coding.entity.Customer;
import com.lti.coding.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/customer/add")
	public String createCustomer(@RequestParam Customer customer) {
		return this.customerService.createCustomer(customer);
	}

	@PostMapping("/customer/update")
	public String updateCustomer(@RequestParam Customer customer) {
		return this.customerService.updateCustomer(customer);
	}

	@PostMapping("/customer/remove/{id}")
	public String removeCustomer(@PathVariable int id) {
		return this.customerService.removeCustomer(id);
	}

	@GetMapping("/customer")
	public List<Customer> getDetails() {

		return this.customerService.getDetails();
	}

}
