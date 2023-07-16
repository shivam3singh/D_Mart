package com.dmart.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmart.model.Admin;
import com.dmart.model.CurrentUserSession;
import com.dmart.model.LogInDTO;
import com.dmart.repo.AdminRepo;
import com.dmart.repo.UserSessionRepo;
import org.apache.commons.lang3.RandomStringUtils;
////...
//
//String randomString = RandomStringUtils.randomAlphanumeric(10);
//System.out.println("Random String: " + randomString);

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private AdminRepo cDao;

	@Autowired
	private UserSessionRepo sDao;

	@Override
	public String logIntoAccount(LogInDTO dto) throws LoginException {

		Admin existingCustomer = cDao.findByMobileNo(dto.getMobileNumber());

		if (existingCustomer == null) {

			throw new LoginException("Please Enter a valid mobile number");

		}

		Optional<CurrentUserSession> validCustomerSessionOpt = sDao.findById(existingCustomer.getAdminId());

		if (validCustomerSessionOpt.isPresent()) {

			throw new LoginException("User already Logged In with this number");

		}

		if (existingCustomer.getPassword().equals(dto.getPassword())) {

			String key = RandomStringUtils.randomAlphanumeric(6);
			// String key= RandomString.make(6);

			CurrentUserSession currentUserSession = new CurrentUserSession(existingCustomer.getAdminId(), key,
					LocalDateTime.now());

			sDao.save(currentUserSession);

			return currentUserSession.toString();
		} else
			throw new LoginException("Please Enter a valid password");

	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {

		CurrentUserSession validCustomerSession = sDao.findByUuid(key);

		if (validCustomerSession == null) {
			throw new LoginException("User Not Logged In with this number");

		}

		sDao.delete(validCustomerSession);

		return "Logged Out !";

	}

}
