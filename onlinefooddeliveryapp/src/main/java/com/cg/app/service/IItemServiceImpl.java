package com.cg.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.app.entity.Category;
import com.cg.app.entity.Item;
import com.cg.app.entity.Restaurant;
import com.cg.app.exceptions.ItemException;
import com.cg.app.exceptions.RestaurantException;
import com.cg.app.repository.ICategoryRepository;
import com.cg.app.repository.IItemRepository;
import com.cg.app.repository.IRestaurantRepository;

@Service
public class IItemServiceImpl implements IItemService {
	
	@Autowired
	private IItemRepository itemRepo;

	@Autowired
	private IRestaurantRepository restRepo;
	
	@Autowired
	private ICategoryRepository catRepo;
	
	
	@Override
	public Item addItem(Item item) {
		if(item!=null) {
			return itemRepo.save(item);
		}
		throw new ItemException("Please Check Your Data");
	}

	@Override
	public Item updateItem(Item item) throws ItemException {
		
		if(itemRepo.existsById(item.getItemId())) {
			return itemRepo.save(item);
		}
		throw new ItemException("Item Not Updated");
	
	
	}

	@Override
	public Item removeItem(Integer itemId) throws ItemException {
		
	 Item item=itemRepo.findById(itemId).orElseThrow(() -> new ItemException("Item Not found with id "+itemId));
		
	 itemRepo.delete(item);
	 
	 return item;
		
	}

	@Override
	public Item viewItem(Integer itemId) throws ItemException {
		
		 return itemRepo.findById(itemId).orElseThrow(() -> new ItemException("Item Not found with id "+itemId));
			
	}

	@Override
	public List<Item> viewAllItems(Integer restaurantId) throws RestaurantException {
	
		Optional<Restaurant> opt= restRepo.findById(restaurantId);
		
		if(opt.isPresent()) {
			
			return opt.get().getItemList();
		}
		else
			throw new RestaurantException("Restaurant does not exist with the Id "+restaurantId);
		
		
		
	}

	@Override
	public List<Item> viewAllItems(String catName) throws ItemException {
		
		
		Category category= catRepo.findByCategoryName(catName);
		
		List<Item> items= category.getItems();
		
		
		if(items.size() >0)
			return items;
		else
			throw new ItemException("There is no any Item in This Category "+catName);
		
	}

	@Override
	public List<Item> viewAllItemsByName(String itmName) throws ItemException {

		List<Item> itmList = itemRepo.findAll();
		
		List<Item> filItemList = new ArrayList<Item>();
		
		for(Item itm : itmList) {
			if(itm.getItemName().equalsIgnoreCase(itmName)) {
				filItemList.add(itm);
			}
		}
		if(filItemList.size()>0) {
			return filItemList;
		}
		throw new ItemException("Items not found");
		
		
	}
	

	public List<Item> viewAllItems(){
		List<Item> items= itemRepo.findAll();
		
		if(items.size() > 0)
			return items;
		else
			throw new ItemException("there is no any item yet...");
	}

	@Override
	public Restaurant addItemToRestaurant(Integer restId, Item item) throws RestaurantException, ItemException {
		
		 Restaurant rest= restRepo.findById(restId).orElseThrow(() -> new RestaurantException("Invalid Restautant Id "+restId) );
		
		 
		 Category cat= item.getCategory();
		 
		 Category existingCat= catRepo.findByCategoryName(cat.getCategoryName());
		 
		 
		 if(existingCat == null) {
			 
			 Category newCat=new Category();
			 newCat.setCategoryName(cat.getCategoryName());
			 
			 Category newSavedCat= catRepo.save(newCat);
			 item.setCategory(newSavedCat);
			 Item saveditem= itemRepo.save(item);
			 rest.getItemList().add(saveditem);
			 return restRepo.save(rest);
		 }else
		 {		 	item.setCategory(existingCat);
		 	
			 Item savedItem= itemRepo.save(item);
			 
			 rest.getItemList().add(savedItem);
			 
			return restRepo.save(rest);
		 } 
	}	
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		
	}


