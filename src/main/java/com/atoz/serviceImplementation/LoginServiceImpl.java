package com.atoz.serviceImplementation;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atoz.exception.LoginException;
import com.atoz.model.CurrentUserSession;
import com.atoz.model.Customer;
import com.atoz.model.User;
import com.atoz.repository.CustomerDao;
import com.atoz.repository.UserSessionDao;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private CustomerDao custD;
	
	@Autowired
	private UserSessionDao sessionD;

	@Override
	public String LoginYourAccount(User user) throws LoginException{
		
		
		if(user.getRole().equals("admin")) {
			System.out.println("***************************************");
			String key = RandomString.make(6);
	        Random rand = new Random();

	        int rand_int1 = rand.nextInt(100);

			
			CurrentUserSession userSession = new CurrentUserSession(rand_int1,key,LocalDateTime.now(),user.getEmail(),user.getPassword(),user.getRole());
			sessionD.save(userSession);
			return userSession.toString();
			
			
			
		}
		
		
	
		Customer existingCust = custD.findByEmail(user.getEmail());
		
		if( existingCust == null ) {
			
			throw new LoginException("Please Enter Valid Email");
		}
		
		
		Optional<CurrentUserSession> checkLogin = sessionD.findById(existingCust.getCustomerId());
		
		if( checkLogin.isPresent()) {
			throw new LoginException("Very Bad You already Login Man");
		}
		
		if( existingCust.getPassword().equals(user.getPassword())) {
			
			String key = RandomString.make(6);
			
			CurrentUserSession userSession = new CurrentUserSession(existingCust.getCustomerId(),key,LocalDateTime.now(),user.getEmail(),user.getPassword(),user.getRole());
			sessionD.save(userSession);
			return userSession.toString();
			
		}
		else 
			throw new LoginException("Please Enter Valid Password");
	}

	
	@Override
	public String LogoutYourAccount(String key) throws LoginException{
		
		
		CurrentUserSession validUserSession =  sessionD.findByUuid(key);
		
		if( validUserSession == null ) {
			
			throw new LoginException("User Not Logged in with this Email Id");
		}
		
		sessionD.delete(validUserSession);
		
		return "Logged Out!";
	}

}
