package com.cg.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.app.entity.Restaurant;
import com.cg.app.exceptions.RestaurantException;

@Service
public class IRestaurantServiceImpl implements IRestaurantService{

	@Override
	public Restaurant addRestaurant(Restaurant res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Restaurant updateRestaurant(Restaurant res) throws RestaurantException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Restaurant removeRestaurant(Integer restId) throws RestaurantException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Restaurant viewRestaurant(Integer restId) throws RestaurantException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Restaurant> viewNearByRestaurant(String location) throws RestaurantException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Restaurant> viewRestaurantByItemName(String name) throws RestaurantException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
