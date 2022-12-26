package com.atoz.serviceImplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.atoz.exception.CustomerException;
import com.atoz.exception.LoginException;
import com.atoz.model.CurrentUserSession;
import com.atoz.model.Customer;
import com.atoz.repository.CustomerDao;
import com.atoz.repository.UserSessionDao;
@org.springframework.stereotype.Service
public class Validation {
	
	@Autowired
	private UserSessionDao currentuser;
	
	@Autowired
	private CustomerDao custDao;
	
	
	public Customer validateLogin(String key) throws LoginException,CustomerException{
		CurrentUserSession checkCustomer = currentuser.findByUuid(key);
		
		if(checkCustomer == null) throw new LoginException("Customer not logged in");
		
		Customer loggedCustomer  = custDao.findById(checkCustomer.getUserId()).orElseThrow(()-> new CustomerException("No Such Customer in Db"));
		
		return loggedCustomer;

		
		
	}

}
