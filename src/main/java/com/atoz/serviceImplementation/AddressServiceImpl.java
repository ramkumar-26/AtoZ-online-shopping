package com.atoz.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atoz.exception.AddressException;
import com.atoz.exception.CustomerException;
import com.atoz.exception.LoginException;
import com.atoz.model.Address;
import com.atoz.model.Customer;
import com.atoz.repository.AddressDao;
import com.atoz.repository.CustomerDao;
import com.atoz.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	AddressDao addressRepo;
	
	@Autowired
	Validation valid;
	@Autowired
	CustomerDao custRepo;
	
	@Override
	public Address addAddres(Address address,String key) throws LoginException, CustomerException {
		Customer customer = valid.validateLogin(key);
		if(customer.getAddress()!=null) {
			throw new AddressException("Address is already present! Please use update option to add any changes!");
		}
		Address new_add = addressRepo.save(address);
		return new_add;
	}

	@Override
	public Address updateAddress(Address address,String key) throws LoginException, CustomerException {
		Customer customer = valid.validateLogin(key);
		if(customer.getAddress()==null) {
			throw new AddressException("No address is added to update! Please use add option to add a address!");
		}
		Address updated_add = addressRepo.save(address);
		return updated_add;
	}

	@Override
	public Address removeAddress(String key) throws LoginException, CustomerException {
		Customer customer = valid.validateLogin(key);
		if(customer.getAddress()==null) {
			throw new AddressException("You Don't have any address to remove!");
		}
		Address add = customer.getAddress();
		addressRepo.delete(add);
		return add;
	}

	@Override
	public List<Address> viewAllAddress() {
		List<Address> list = addressRepo.findAll();
		if(list.isEmpty()) {
			throw new AddressException("Address Table is Empty!");
		}
		return list;
	}

	@Override
	public Address viewAddressByUserId(Integer userId) throws CustomerException {
		Optional<Customer> customer = custRepo.findById(userId);
		if(customer.isEmpty())
			throw new CustomerException("Can't find customer with userId :"+userId);
		
		Address add = customer.get().getAddress();
		
		return add;
	}
	
}
