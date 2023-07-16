package com.dmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dmart.exception.AdminException;
import com.dmart.model.Admin;
import com.dmart.service.AdminService;


@RestController
public class AdminController {

	@Autowired
	private AdminService cService;
	
	
	@PostMapping("/Admin")
	public ResponseEntity<Admin> saveAdmin(@RequestBody Admin Admin) throws AdminException {
		
		Admin savedAdmin= cService.createAdmin(Admin);
		
		
		return new ResponseEntity<Admin>(savedAdmin,HttpStatus.CREATED);
	}
	
	@PutMapping("/Admins")
	public  ResponseEntity<Admin> updateAdmin(@RequestBody Admin Admin,@RequestParam(required = false) String key ) throws AdminException {
		
		
		Admin updatedAdmin= cService.updateAdmin(Admin, key);
				
		return new ResponseEntity<Admin>(updatedAdmin,HttpStatus.OK);
		
	}
	


	
	
	
	
	
	
	
	
	
}
