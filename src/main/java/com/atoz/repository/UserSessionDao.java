package com.atoz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atoz.model.CurrentUserSession;

public interface UserSessionDao extends JpaRepository<CurrentUserSession, Integer>{
	
	public CurrentUserSession findByUuid(String uuid);

}
