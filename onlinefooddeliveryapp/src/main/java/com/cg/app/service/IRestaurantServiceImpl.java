package com.cg.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.app.entity.Item;
import com.cg.app.entity.Restaurant;
import com.cg.app.exceptions.RestaurantException;
import com.cg.app.repository.IRestaurantRepository;

@Service
public class IRestaurantServiceImpl implements IRestaurantService{

	@Autowired
	private IRestaurantRepository restaurantRepo;
	
	
	@Override
	public Restaurant addRestaurant(Restaurant res) {
		
		return restaurantRepo.save(res);
		
	}

	@Override
	public Restaurant updateRestaurant(Restaurant res) throws RestaurantException {
		
		if(restaurantRepo.existsById(res.getRestaurantId())) {
			return restaurantRepo.save(res);
		}
		throw new RestaurantException("Invalid Restaurant data");
		
		
		
	}

	@Override
	public Restaurant removeRestaurant(Integer restId) throws RestaurantException {
		
		Optional<Restaurant> opt= restaurantRepo.findById(restId);
		
		if(opt.isPresent()) {
			
			Restaurant rest= opt.get();
			
			restaurantRepo.delete(rest);
			
			return rest;
			
		}
		else
			throw new RestaurantException("Restaurant does not exist with the ID "+restId);
		
	}

	@Override
	public Restaurant viewRestaurant(Integer restId) throws RestaurantException {

		Optional<Restaurant> opt= restaurantRepo.findById(restId);
		
		if(opt.isPresent()) {
			
			Restaurant rest= opt.get();	
			return rest;
			
		}
		else
			throw new RestaurantException("Restaurant does not exist with the ID "+restId);
		
	}

	@Override
	public List<Restaurant> viewNearByRestaurant(String location) throws RestaurantException {

		
		List<Restaurant> restList = restaurantRepo.findAll();
		List<Restaurant> filterdList = new ArrayList<Restaurant>();
		
		for(Restaurant res : restList) {
				if(res.getAddress().getCity().toLowerCase().equals(location.toLowerCase())) {
					filterdList.add(res);
				}
		}
		
		if(filterdList.size()>0)
			return filterdList;
		else
			throw new RestaurantException("No Restaurants at "+location);
		
		
		
	}

	@Override
	public List<Restaurant> viewRestaurantByItemName(String itemName) throws RestaurantException {
		
		List<Restaurant> restList = restaurantRepo.findAll();
		
		List<Restaurant> filterdList = new ArrayList<Restaurant>();
		
		for(Restaurant res : restList) {
		
			for(Item itm :res.getItemList()) {
				if(itm.getItemName().equalsIgnoreCase(itemName)) {
					filterdList.add(res);
					break;
				}
			}
		}
		if(filterdList.size()>0)
			return filterdList;
		else
			throw new RestaurantException(itemName+" not found in any Restaurant");	 
		
		
		
		
	}
	
}
