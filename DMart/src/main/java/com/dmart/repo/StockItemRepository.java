package com.dmart.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmart.model.StockItem;

public interface StockItemRepository extends JpaRepository<StockItem, Long> {
	
	
	//findByLocation
}