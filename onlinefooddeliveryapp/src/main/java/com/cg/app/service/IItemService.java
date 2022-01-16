package com.cg.app.service;

import java.util.List;

import com.cg.app.entity.Item;
import com.cg.app.exceptions.ItemException;
import com.cg.app.exceptions.RestaurantException;



public interface IItemService {

	public Item addItem(Item item)throws ItemException; 

	public Item updateItem(Item item) throws ItemException;

	public Item removeItem(Integer itemId) throws ItemException;

	public Item viewItem(Integer itemId) throws ItemException;

	public List<Item> viewAllItems(Integer restaurantId) throws RestaurantException;

	public List<Item> viewAllItems(String catName) throws ItemException;

	public List<Item> viewAllItemsByName(String itmName) throws ItemException;
	
	public List<Item> viewAllItems()throws ItemException;
}
