package com.atoz.service;

import java.util.List;

import com.atoz.exception.CustomerException;
import com.atoz.model.Customer;

	public interface CustomerService {

		Customer addCustomer(Customer cust) throws CustomerException;
		
		Customer updateCustomer(Customer cust, String key) throws CustomerException;
		
		Customer removeCustomer(Integer custId, String key) throws CustomerException;
		
		Customer viewCustomer(Integer custId) throws CustomerException;
		
		List<Customer> viewAllCustomer(String location) throws CustomerException;
			
	}