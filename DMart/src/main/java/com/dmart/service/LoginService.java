package com.dmart.service;

import javax.security.auth.login.LoginException;

import com.dmart.model.LogInDTO;

public interface LoginService {
	
	public String logIntoAccount(LogInDTO dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;

}
