package com.dmart.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dmart.model.StoreLocation;

public interface StockLocationRepo extends JpaRepository<StoreLocation, Long> {

//	@Query("Select c.name from c.store_Location where c.name=?")
//	public  String findByName(String name);
//	
//	  @Query("select c.username from Customer c")
//    public List<String> getAllUsernames();

}
