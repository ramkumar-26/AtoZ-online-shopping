package com.atoz.service;

import java.util.List;

import com.atoz.exception.CustomerException;
import com.atoz.exception.LoginException;
import com.atoz.model.Address;

public interface AddressService {
	public Address addAddres(Address address,String key) throws LoginException, CustomerException;
	public Address updateAddress(Address address,String key) throws LoginException, CustomerException;
	public Address removeAddress( String key) throws LoginException, CustomerException;
	public List<Address> viewAllAddress();
	public Address viewAddressByUserId(Integer userId) throws CustomerException;

}
