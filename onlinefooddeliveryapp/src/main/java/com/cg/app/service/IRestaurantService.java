package com.cg.app.service;

import java.util.List;

import com.cg.app.entity.Restaurant;
import com.cg.app.exceptions.RestaurantException;

public interface IRestaurantService {
	
	public Restaurant addRestaurant(Restaurant res);
	
	public Restaurant updateRestaurant(Restaurant res) throws RestaurantException;
	
	public Restaurant removeRestaurant(Integer restId) throws RestaurantException;
	public Restaurant viewRestaurant(Integer restId) throws RestaurantException;
	public List<Restaurant> viewNearByRestaurant(String location) throws RestaurantException;
	public List<Restaurant> viewRestaurantByItemName(String name)throws RestaurantException;

}
