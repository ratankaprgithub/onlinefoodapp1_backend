package com.cg.app.service;

import org.springframework.stereotype.Service;

import com.cg.app.entity.FoodCart;
import com.cg.app.entity.Item;
import com.cg.app.exceptions.CartException;
import com.cg.app.exceptions.ItemException;

@Service
public class ICartServiceImpl implements ICartService {

	@Override
	public FoodCart addItemToCart(FoodCart cart, Item item) throws CartException, ItemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodCart reduceQuantity(FoodCart cart, Item item, int quantity) throws CartException, ItemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodCart removeItem(FoodCart cart, Item item) throws ItemException, CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodCart clearCart(FoodCart cart) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FoodCart increaseQuantity(FoodCart cart, Item item, Integer quantity) throws ItemException {
		// TODO Auto-generated method stub
		return null;
	}

		
}
