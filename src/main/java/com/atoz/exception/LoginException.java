package com.atoz.exception;


public class LoginException extends RuntimeException{
	
	String message;
	
	public LoginException() {
		
	}

	public LoginException(String message) {
		super(message);
		
	}
	
	


}
