package com.cg.app.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.app.entity.Restaurant;
import com.cg.app.service.IRestaurantService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	private IRestaurantService restaurantService;
	
	/*
	
	url:- http://localhost:7000/restaurant/add
	method:-post 
	
	request data:-
	
		
	{
	 		"restaurantName": "alpha",
             "managerName": "Ravi",
             "contactNumber": "9756434",
             "address": {

                    "buildingName": "bul5",
                    "streetNumber": "st5",
                    "area": "ar5",
                    "city": "Hydrabad",
                    "state": "Telangana",
                    "country": "IND",
                    "pincode": "542323"

             }
}
	
	
	*/
	
	
	@PostMapping("/add")
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant res){
		 Restaurant rest= restaurantService.addRestaurant(res);
	 
			return new ResponseEntity<Restaurant>(rest,HttpStatus.OK);
	
	}
	
	/*
	
	//first add a restaurant without Item, then add an item with category seperatly
	 //then update the restaurant with the new item
	
	url:- http://localhost:7000/restaurant/update
	method:- put
	
	request data:-
	
	{
    "restaurantId": 3,
    "restaurantName": "alpha",
    "managerName": "Ravi",
    "contactNumber": "9756434",
    "itemList": [
        {
            "itemId": 8,
            "itemName": "idli",
            "quantity": 50,
            "cost": 100,
            "category": {
                "catId": 6,
                "categoryName": "south-indian"
            }
            
        }
    ],
    "address": {
        "addressId": 4,
        "buildingName": "bul5",
        "streetNumber": "st5",
        "area": "ar5",
        "city": "Hydrabad",
        "state": "Telangana",
        "country": "IND",
        "pincode": "542323"
    }
}
	
	
	
	
	
	
	
	*/
	
	@PutMapping("/update")
	public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant res){
		Restaurant restaurant =restaurantService.updateRestaurant(res);
		 
			return new ResponseEntity<Restaurant>(restaurant,HttpStatus.OK);
			
	}
	
	@GetMapping("/view/{restaurantId}")
	public ResponseEntity<Restaurant> viewRestaurant(@PathVariable("restaurantId") Integer restaurantId) {
		Restaurant restaurant = restaurantService.viewRestaurant(restaurantId);
			return new ResponseEntity<Restaurant>(restaurant,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/remove/{restaurantId}")
	public Restaurant removeRestaurant(@PathVariable ("restaurantId") Integer restaurantId) {
		
		return restaurantService.removeRestaurant(restaurantId);
		
	}
	

	
	@GetMapping("/viewbyitem/{itemname}")
	public List<Restaurant> viewRestaurantByItemName(@PathVariable("itemname") String itemName) {
		
		return restaurantService.viewRestaurantByItemName(itemName);
		
	}
	
	@GetMapping("/viewbylocation/{location}")
	public List<Restaurant> viewRestaurantByLocation(@PathVariable("location") String location){
		return restaurantService.viewNearByRestaurant(location);
		
	}
	

	
	
	
}


