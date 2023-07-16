package com.dmart.service;

import java.util.List;

import com.dmart.exception.StockItemException;
import com.dmart.exception.UserSessionException;
import com.dmart.model.StockItem;

import com.dmart.model.StockMovementDTO;

import com.dmart.model.StoreLocation;

public interface StockItemService {
   
	 public StockItem createStockItem(String uniqueKey,StockItem stockItem)throws UserSessionException;
    void deleteStockItem(String uniqueKey,Long itemId)throws StockItemException, UserSessionException;
    public StockItem updateStockItem(String uniqueKey,Long stockItemId, Integer updatedStockItemQuantity)throws StockItemException, UserSessionException;
    public StockMovementDTO moveStockItem(String uniqueKey,Long stockItemId, Long fromStoreLocationId, Long toStoreLocationId)throws StockItemException , UserSessionException;
    
    public List<StoreLocation> getAllStockItemsQuantity() throws StockItemException;
    
    public StoreLocation createLocation(String uniqueKey,StoreLocation storeLocation)throws UserSessionException,StockItemException;
    
    public StoreLocation addStocktoLocation(String uniqueKey,Long locationId,Long StockItemid)throws UserSessionException,StockItemException;
    
}
