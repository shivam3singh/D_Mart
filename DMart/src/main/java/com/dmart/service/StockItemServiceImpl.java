package com.dmart.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmart.exception.StockItemException;
import com.dmart.exception.UserSessionException;
import com.dmart.model.Admin;
import com.dmart.model.CurrentUserSession;
import com.dmart.model.StockItem;
import com.dmart.model.StockMovementDTO;
import com.dmart.model.StoreLocation;
import com.dmart.repo.AdminRepo;
import com.dmart.repo.StockItemRepository;
import com.dmart.repo.StockLocationRepo;
import com.dmart.repo.UserSessionRepo;

@Service
public class StockItemServiceImpl implements StockItemService {

	@Autowired
	private StockItemRepository stockItemRepo;

	@Autowired
	private StockLocationRepo LocationRepo;

	@Autowired
	private UserSessionRepo userSessionRepo;

	@Autowired
	private AdminRepo adminRepo;

	// @SuppressWarnings("deprecation")
	@Override
	public StockItem createStockItem(String uniqueKey, StockItem stockItem) throws UserSessionException {

		CurrentUserSession loggedInUser = userSessionRepo.findByUuid(uniqueKey);

		if (loggedInUser == null) {
			throw new UserSessionException("Please provide a valid key to create a stock");
		}
	
	
		return stockItemRepo.save(stockItem);

	}

	@Override
	public void deleteStockItem(String uniqueKey, Long itemId) throws StockItemException, UserSessionException {
		// TODO Auto-generated method stub

		CurrentUserSession loggedInUser = userSessionRepo.findByUuid(uniqueKey);

		if (loggedInUser == null) {
			throw new UserSessionException("Please provide a valid key to delete a stock");
		}
		// loggedInUser.getUserId();
		Optional<Admin> adm = adminRepo.findById(loggedInUser.getUserId());

		if (adm.isPresent()) {
			// return stockItemRepo.save(stockItem);
			Optional<StockItem> item = stockItemRepo.findById(itemId);

			if (item.isPresent()) {
				stockItemRepo.delete(item.get());
			} else {
				throw new StockItemException("No Item Found with this id");
			}
		}

		else
			throw new UserSessionException("Invalid Admin Details, please login first");

	}

	@Override
	public StockItem updateStockItem(String uniqueKey, Long stockItemId, Integer updatedStockItemQuantity)
			throws StockItemException, UserSessionException {
//	
		CurrentUserSession loggedInUser = userSessionRepo.findByUuid(uniqueKey);

		if (loggedInUser == null) {
			throw new UserSessionException("Please provide a valid key to create a stock");
		}
		// loggedInUser.getUserId();
		Optional<Admin> adm = adminRepo.findById(loggedInUser.getUserId());

		if (adm.isPresent()) {
			// return stockItemRepo.save(stockItem);
			Optional<StockItem> item = stockItemRepo.findById(stockItemId);

			if (item.isPresent()) {

				item.get().setQuantity(updatedStockItemQuantity);

				return stockItemRepo.save(item.get());
				// return item.get();
			} else {
				throw new StockItemException("No Item Found with this id");
			}
		}

		else
			throw new UserSessionException("Invalid Admin Details, please login first");

	}

	@Override
	public StockMovementDTO moveStockItem(String uniqueKey, Long stockItemId, Long fromStoreLocationId,
			Long toStoreLocationId) throws StockItemException, UserSessionException {
		

		CurrentUserSession loggedInUser = userSessionRepo.findByUuid(uniqueKey);

		if (loggedInUser == null) {
			throw new UserSessionException("Please provide a valid key to create a stock");
		}
		// loggedInUser.getUserId();
		Optional<Admin> adm = adminRepo.findById(loggedInUser.getUserId());

		if (adm.isPresent()) {
			// return stockItemRepo.save(stockItem);
			StockMovementDTO stockMove = new StockMovementDTO();

			Optional<StoreLocation> sL1 = LocationRepo.findById(fromStoreLocationId);

			if (sL1.isPresent()) {

				Optional<StockItem> item = stockItemRepo.findById(stockItemId);

				if (item.isPresent()) {

					Optional<StoreLocation> sL2 = LocationRepo.findById(toStoreLocationId);

					if (sL2.isPresent()) {

						stockMove.setFromStoreLocationName(sL1.get().getName());
						stockMove.setToStoreLocationName(sL2.get().getName());
						stockMove.setStockItemName(item.get().getName());
						stockMove.setMovementTime(LocalDateTime.now());

						item.get().setStoreLocation(sL2.get());
						sL2.get().getStockItems().add(item.get());
						LocationRepo.save(sL2.get());

					} else {
						throw new StockItemException("No Location Found to ToLocation to move ");

					}

				} else {
					throw new StockItemException("No Item Found with this id");
				}

			} else {
				throw new StockItemException("No Location found of FromLocation to move");
			}

			return stockMove;

		}

		else
			throw new UserSessionException("Invalid Admin Details, please login first");

	}

	@Override
	public List<StoreLocation> getAllStockItemsQuantity() throws StockItemException {
		// TODO Auto-generated method stub
		// List<StockItem> list=new ArrayList<>();
		List<StoreLocation> storloc = LocationRepo.findAll();

		if (storloc.isEmpty()) {
			throw new StockItemException("No Item found at any Location");
		}

		return storloc;
	}

	@Override
	public StoreLocation createLocation(String uniqueKey, StoreLocation storeLocation)
			throws UserSessionException, StockItemException {
		// TODO Auto-generated method stub

		CurrentUserSession loggedInUser = userSessionRepo.findByUuid(uniqueKey);

		if (loggedInUser == null) {
			throw new UserSessionException("Please provide a valid key to create a stock");
		} else {
			// stockItem.getStoreLocation().getStockItems().add(stockItem);
			List<StockItem> list = storeLocation.getStockItems();
			list.forEach(s -> {
				s.setStoreLocation(storeLocation);
			});

			return LocationRepo.save(storeLocation);
		}

	}

	@Override
	public StoreLocation addStocktoLocation(String uniqueKey, Long locationId, Long StockItemid)
			throws UserSessionException, StockItemException {
		CurrentUserSession loggedInUser = userSessionRepo.findByUuid(uniqueKey);

		if (loggedInUser == null) {
			throw new UserSessionException("Please provide a valid key to create a stock");
		} else {
			// stockItem.getStoreLocation().getStockItems().add(stockItem);
			Optional<StoreLocation> l = LocationRepo.findById(locationId);
			if (l.isPresent()) {
				Optional<StockItem> st = stockItemRepo.findById(StockItemid);
				if (st.isPresent()) {
					l.get().getStockItems().add(st.get());

					st.get().setStoreLocation(l.get());

					return LocationRepo.save(l.get());

				} else {
					throw new UserSessionException("No Stock present with this id");
				}

			} else {
				throw new UserSessionException("No Location present with this id");
			}
		}

	}

}
