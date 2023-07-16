package com.dmart.service;

import com.dmart.exception.AdminException;
import com.dmart.model.Admin;

public interface AdminService {
 public Admin createAdmin(Admin admin)throws AdminException;
 
 public Admin updateAdmin(Admin admin,String Key)throws AdminException;
  
}
