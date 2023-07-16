package com.dmart.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmart.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{

	public Admin  findByMobileNo(String mobileNo);
	
}
