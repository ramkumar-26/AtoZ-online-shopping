package com.atoz.serviceImplementation;

import com.atoz.exception.LoginException;
import com.atoz.model.User;

public interface LoginService {
	
	public String LoginYourAccount(User user) throws LoginException;
	public String LogoutYourAccount(String key) throws LoginException;

}
