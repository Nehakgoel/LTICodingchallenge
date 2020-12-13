package com.lti.coding.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.lti.coding.dao.CustomerDao;
import com.lti.coding.entity.Customer;

@ExtendWith(MockitoExtension.class)
@Transactional

class CustomerServiceTest {
	
	CustomerService customerService = new CustomerService();
	
	@Mock
	CustomerDao customerDao;
	
	 @BeforeEach
	  void setUp() {
		 
		 
		 this.customerService.customerDao = this.customerDao;
		 Mockito.when(this.customerService.getDetails()).thenReturn(this.createCustomerList());
	  }

	@Rollback
	@Test
	void testCreateCustomer() {
		
		Customer customer = new  Customer();
		customer.setFirstName("ABC");
		customer.setLastName("XYZ");
		customer.setAddress("12345");
		customer.setPhoneNumber(58964);
		customer.setSocialSecurityNumber(123456);
		
		String message =this.customerService.createCustomer(customer);
		assertEquals("Customer created successfully", message);
		
	}

	@Rollback
	@Test
	void testUpdateCustomer() {
		Customer customer = new  Customer();
		customer.setFirstName("123");
		customer.setLastName("XYZ");
		customer.setAddress("12345");
		customer.setPhoneNumber(58964);
		customer.setSocialSecurityNumber(123456);
		
		String message =this.customerService.createCustomer(customer);
		assertEquals("Customer updated successfully", message);
	}

	@Rollback
	@Test
	void testRemoveCustomer() {
		String message =this.customerService.removeCustomer(1);
		assertEquals("Customer removed successfully", message);
	}

	@Test
	void testGetDetails() {
		assertEquals(1, this.customerService.getDetails().size());
	}
	
	private List<Customer> createCustomerList(){
		List<Customer>  cusList = new ArrayList<Customer>();
		cusList.add(this.createCustomerData());
		return cusList;
	}
	
	private Customer createCustomerData() {
		Customer cus = new Customer();
		
		cus.setAddress("123456789");
		cus.setFirstName("ABC");
		cus.setLastName("XYZ");
		cus.setPhoneNumber(123);
		cus.setSocialSecurityNumber(1234);
		return cus;
	}

}
