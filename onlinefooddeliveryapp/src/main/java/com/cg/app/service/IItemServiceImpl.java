package com.cg.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.app.entity.Item;
import com.cg.app.exceptions.ItemException;
import com.cg.app.exceptions.RestaurantException;
import com.cg.app.repository.IItemRepository;

@Service
public class IItemServiceImpl implements IItemService {
	
	@Autowired
	private IItemRepository itemRepo;

	
	@Override
	public Item addItem(Item item) {
		if(item!=null) {
			return itemRepo.save(item);
		}
		throw new ItemException("Please Check Your Data");
	}

	@Override
	public Item updateItem(Item item) throws ItemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item removeItem(Integer itemId) throws ItemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item viewItem(Integer itemId) throws ItemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> viewAllItems(Integer restaurantId) throws RestaurantException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> viewAllItems(String catName) throws ItemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> viewAllItemsByName(String itmName) throws ItemException {
		// TODO Auto-generated method stub
		return null;
	}

}
