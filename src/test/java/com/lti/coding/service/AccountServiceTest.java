package com.lti.coding.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.lti.coding.dao.AccountDao;
import com.lti.coding.dao.CustomerDao;
import com.lti.coding.entity.Account;

class AccountServiceTest {
	
AccountService accountService = new AccountService();
	
	@Mock
	AccountDao accountDao;
	
	 @BeforeEach
	  void setUp() {
		 
		 
		 this.accountService.accountDao = this.accountDao;
		// Mockito.when(this.customerService.getDetails()).thenReturn(this.createCustomerList());
	  }

	@Test
	void testCreateAccount() {
		Account acc = new Account();
		acc.setAmount(new Double(12345));
		acc.setType("SAVINGS");
		
		String message = this.accountService.createAccount(acc);
		assertEquals("Account Created Successfully", message);
	}

	@Test
	void testUpdateAccount() {
		Account acc = new Account();
		acc.setAmount(new Double(12345));
		acc.setType("SAVINGS");
		
		String message = this.accountService.updateAccount(acc);
		assertEquals("Account updated Successfully", message);
	}

	@Test
	void testRemoveAccount() {
			
		String message = this.accountService.removeAccount(1);
		assertEquals("Account removed Successfully", message);
	}

	

}
