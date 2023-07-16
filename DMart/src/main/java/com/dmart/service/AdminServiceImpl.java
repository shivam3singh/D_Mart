package com.dmart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmart.exception.AdminException;
import com.dmart.model.Admin;
import com.dmart.model.CurrentUserSession;
import com.dmart.repo.AdminRepo;
import com.dmart.repo.UserSessionRepo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo adminRepo;

	private UserSessionRepo userSessionRepo;

	@Override
	public Admin createAdmin(Admin admin) throws AdminException {
		// TODO Auto-generated method stub

		Admin existingAdmin = adminRepo.findByMobileNo(admin.getMobileNo());

		if (existingAdmin == null) {
			return adminRepo.save(admin);
		} else {

			throw new AdminException("Admin already exist with this Credentials");
		}

	}

	@Override
	public Admin updateAdmin(Admin admin, String Key) throws AdminException {

		CurrentUserSession user = userSessionRepo.findByUuid(Key);

		if (user == null) {

			throw new AdminException("Please Enter a valid Key");
		} else {
			if (admin.getAdminId() == user.getUserId()) {
				return adminRepo.save(admin);
			} else {
				throw new AdminException("Invalid Credentials ! Loggin first");
			}

		}
	}

}
