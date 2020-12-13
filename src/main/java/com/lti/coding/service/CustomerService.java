package com.lti.coding.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.coding.dao.CustomerDao;
import com.lti.coding.entity.Account;
import com.lti.coding.entity.Customer;

@Service
public class CustomerService {


	@Autowired
	public CustomerDao customerDao;	

	public String createCustomer(Customer customer) {

		int  result =  this.customerDao.createCustomer(customer);
		if(result>0) {
			return "Customer created successfully";
		}else {
			return "Error in creating customer";
		}
	}

	public String updateCustomer(Customer customer) {
		int  result =  this.customerDao.updateCustomer(customer);
		if(result>0) {
			return "Customer updated successfully";
		}else {
			return "Error in updating account";
		}
	}

	public String removeCustomer(int id) {
		
		int  result =  this.customerDao.removeCustomer(id);
		
		if(result>0) {
			return "Customer removed successfully";
		}else {
			return "Error in removing account";
		}
	}

	public List<Customer> getDetails() {
		Map<Integer, Customer> cusList = new HashMap<>();
		cusList = this.customerDao.getDetails();
		return (List<Customer>) cusList.values();
	}
}
