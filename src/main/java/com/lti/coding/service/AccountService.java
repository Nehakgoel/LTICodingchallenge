package com.lti.coding.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.lti.coding.dao.AccountDao;
import com.lti.coding.dao.CustomerDao;
import com.lti.coding.entity.Account;

public class AccountService {
	@Autowired
	public AccountDao accountDao;

	public String createAccount(Account account) {

		int  result =  this.accountDao.createAccount(account);
		if(result>0) {
			return "Account Created Successfully";
		}else {
			return "Error in creating account";
		}
	}

	public String updateAccount(Account account) {
		int  result =  this.accountDao.updateAccount(account);
		if(result>0) {
			return "Account updated Successfully";
		}else {
			return "Error in updating account";
		}
	}

	public String removeAccount(int id) {
		int  result =  this.accountDao.removeAccount(id);
		if(result>0) {
			return "Account removed Successfully";
		}else {
			return "Error in removing account";
		}
	}

	public List<Account> transferAmount(int fromId, int toId, double amount){

		Map<Integer, Account> accList = this.accountDao.getAccounts(fromId, toId);

		Account fromAccount = accList.get(fromId);
		fromAccount.setAmount(fromAccount.getAmount()-amount);
		this.accountDao.updateAccount(fromAccount);

		Account toAccount = accList.get(toId);
		toAccount.setAmount(toAccount.getAmount()+amount);
		this.accountDao.updateAccount(toAccount);

		Map<Integer, Account> updatedAccList = this.accountDao.getAccounts(fromId, toId);

		return (List<Account>) updatedAccList.values();
	}

}