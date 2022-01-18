package com.cg.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.app.entity.Category;
import com.cg.app.entity.Item;
import com.cg.app.entity.Restaurant;
import com.cg.app.exceptions.ItemException;
import com.cg.app.service.ICategoryServiceImpl;
import com.cg.app.service.IItemServiceImpl;
import com.cg.app.service.IRestaurantServiceImpl;


@RequestMapping("/item")
@RestController
public class ItemController {

	@Autowired
	IItemServiceImpl itemService;
	
	@Autowired
	IRestaurantServiceImpl restaurantService;

	@Autowired
	ICategoryServiceImpl categoryService;
	
	/*
	
	url:- http://localhost:7000/item/add
	method:- post
	
	request data:- 
	
	 {
        "itemName": "idli",
        "quantity": 8,
        "cost": 120
       
      }
	
	*/
	@PostMapping("/add")
	public Item addItem(@RequestBody Item item) {
		return itemService.addItem(item);
	
	}

	@GetMapping("/view/{itmid}")
	public Item viewItem(@PathVariable("itmid") Integer itmId) {
		
		return itemService.viewItem(itmId);
		
	}

	/*
	
	url:- http://localhost:7000/item/update
	method:-put
	
	request data:-
	
	{
	    "itemId": 9,
	    "itemName": "idli",
	    "quantity": 8,
	    "cost": 120.0,
	    "category": {
	        "catId": 6,
	        "categoryName": "southIndian"
	    }
	    
	}
	
	
	
	*/
	@PutMapping("/update")
	public Item updateItem(@RequestBody Item item) {
		return itemService.updateItem(item);
		
	}

	@DeleteMapping("/remove/{itmid}/{restId}")
	public Item removeItem(@PathVariable("itmid") Integer itemId,@PathVariable("restId") Integer restId) {
	
		Item item= itemService.viewItem(itemId);
		
	 	Restaurant restaurant= restaurantService.viewRestaurant(restId);
		
	 	List<Item> items= restaurant.getItemList();
	 	
	 	if(items.contains(item)) {
	 		items.remove(item);
	 		
	 		restaurantService.updateRestaurant(restaurant);
	 		
	 		return item;
	 	}else
	 		throw new ItemException("Item not found in the Restaurnat");
	 	
	 
	 	
		
	}

	@GetMapping("/viewallItem/rest/{restid}")
	public List<Item> viewAllItemsByRestId(@PathVariable("restid") Integer restId) {
		Restaurant rest = restaurantService.viewRestaurant(restId);
		return rest.getItemList();
		
	}
	
	
	

	@GetMapping("/viewallItem/category/{catName}")
	//category obj with name
	public List<Item> viewAllItemsByCatName(@PathVariable("catName") String catName)  {
		Category cat = categoryService.viewCategory(catName);
		return itemService.viewAllItems(catName);
	}

	
	
	@GetMapping("/viewall/name/{itmname}")
	public List<Item> viewAllItemsByName(@PathVariable("itmname") String itmName)  {
		List<Item> items = itemService.viewAllItemsByName(itmName);
		return items;
	}
	
	//get all items
	@GetMapping("/getall")
	public List<Item> getAllItems()  {
		return itemService.viewAllItems();		
	}
	
	
	/*
	
	{

    "itemName": "gulabjamun",
    "quantity": 50,
    "cost": 100,
    "category": {
        "categoryName": "desert"
    }
    
}
	
	
	*/
	@PutMapping("/add/toRestaurant/{restId}")
	public Restaurant addItemToRestaurant(@PathVariable("restId") Integer restId, @RequestBody Item item) {
		
		return itemService.addItemToRestaurant(restId, item);
		
		
	}
	
	
}