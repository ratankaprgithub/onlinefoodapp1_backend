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
	 		"restaurantName": "bluefox",
             "managerName": "Ratan",
             "contactNumber": "8756434",
             "address": {

                    "buildingName": "bul10",
                    "streetNumber": "st10",
                    "area": "ar10",
                    "city": "chennai",
                    "state": "TN",
                    "country": "IND",
                    "pincode": "342323"

             },
             "itemList": [

                 {
                     "itemName": "dosa",
                     "quantity": 5,
                     "cost": 100,
                     "category": {
                         "categoryName": "southIndian"
                     }
                 },
                 {
                      "itemName": "chickenBirani",
                     "quantity": 7,
                     "cost": 150,
                     "category": {
                         "categoryName": "nonveg"
                     }
                 }


             ]

	 	
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
    "restaurantId": 1,
    "restaurantName": "alpha1",
    "managerName": "Ravi",
    "contactNumber": "756434",
    "itemList": [
        {
            "itemId": 8,
            "itemName": "idli",
            "quantity": 8,
            "cost": 130.0,
            "category": {
                "catId": 5,
                "categoryName": "southIndian"
            },
            "restaurants": []
        }
    ],
    "address": {
        "addressId": 2,
        "buildingName": "bul15",
        "streetNumber": "st15",
        "area": "ar15",
        "city": "hyd",
        "state": "TL",
        "country": "IND",
        "pincode": "42323"
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


