package com.dmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dmart.exception.StockItemException;
import com.dmart.exception.UserSessionException;
import com.dmart.model.StockItem;
import com.dmart.model.StockMovementDTO;
import com.dmart.model.StoreLocation;
import com.dmart.service.StockItemService;

@RestController
public class StockItemController {

	@Autowired
	private StockItemService stockItemService;

	@PostMapping("/StockItem/{uuid}")
	public ResponseEntity<StockItem> createStockItemHandler(@PathVariable("uuid") String uuid,
			@RequestBody StockItem stockitem) throws UserSessionException {
		// return null;

		StockItem s = stockItemService.createStockItem(uuid, stockitem);

		return new ResponseEntity<StockItem>(s, HttpStatus.CREATED);

	}

	@PostMapping("/StoreLocation/{uuid}")
	public ResponseEntity<StoreLocation> createStockItemHandler(@PathVariable("uuid") String uuid,
			@RequestBody StoreLocation storeLocation) throws UserSessionException, StockItemException {
		// return null;

		StoreLocation s = stockItemService.createLocation(uuid, storeLocation);
//		storeLocation.getStockItems().forEach(st->{
//			st.setStoreLocation(storeLocation);
//		});

		return new ResponseEntity<StoreLocation>(s, HttpStatus.CREATED);

	}

	@DeleteMapping("/StockItem/{uuid}/{itemId}")
	public ResponseEntity<StockItem> deleteStockItemHandler(@PathVariable("uuid") String uuid,
			@PathVariable("itemId") Long Itemid) throws StockItemException, UserSessionException {
		// return null;

		stockItemService.deleteStockItem(uuid, Itemid);

		return new ResponseEntity<StockItem>(HttpStatus.OK);

	}

	@PutMapping("/StockItem/{uuid}/{itemId}/{quantity}")
	public ResponseEntity<StockItem> updateStockItemHandler(@PathVariable("uuid") String uuid,
			@PathVariable("itemId") Long Itemid, @PathVariable("quantity") Integer quant)
			throws UserSessionException, StockItemException {
		// return null;

		StockItem s = stockItemService.updateStockItem(uuid, Itemid, quant);

		return new ResponseEntity<StockItem>(s, HttpStatus.CREATED);

	}

	@PutMapping("/StoreLocation/{uuid}/{locationId}/{stockid}")
	public ResponseEntity<StoreLocation> updateLocationwithStock(@PathVariable("uuid") String uuid,
			@PathVariable("locationId") Long Itemid, @PathVariable("stockid") Long stockid)
			throws UserSessionException, StockItemException {
		// return null;

		// StockItem s= stockItemService.updateStockItem(uuid, Itemid, quant);
		StoreLocation s = stockItemService.addStocktoLocation(uuid, Itemid, stockid);

		return new ResponseEntity<StoreLocation>(s, HttpStatus.OK);

	}

	@GetMapping("/StockItematEachLocation/")
	public ResponseEntity<List<StoreLocation>> getStockAtEachLocationandler() throws StockItemException {
		// return null;

		List<StoreLocation> list = stockItemService.getAllStockItemsQuantity();

		return new ResponseEntity<List<StoreLocation>>(list, HttpStatus.OK);

	}
	// String uniqueKey,Long stockItemId,

	@PutMapping("/StockItem/{uuid}/{itemId}/{fromLocationid}/{toLocationid}")
	public ResponseEntity<StockMovementDTO> moveStockHandler(@PathVariable("uuid") String uuid,
			@PathVariable("itemId") Long Itemid, @PathVariable("fromLocationid") Long fromStoreLocationId,
			@PathVariable("toLocationid") Long toStoreLocationId) throws UserSessionException, StockItemException {
		// return null;

		StockMovementDTO s = stockItemService.moveStockItem(uuid, Itemid, fromStoreLocationId, toStoreLocationId);

		return new ResponseEntity<StockMovementDTO>(s, HttpStatus.OK);

	}

}
