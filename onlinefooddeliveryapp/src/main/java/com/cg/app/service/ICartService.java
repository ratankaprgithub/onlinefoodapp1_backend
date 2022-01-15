package com.cg.app.service;

import com.cg.app.entity.FoodCart;
import com.cg.app.entity.Item;
import com.cg.app.exceptions.CartException;
import com.cg.app.exceptions.ItemException;

public interface ICartService {

	public FoodCart addItemToCart(FoodCart cart, Item item) throws CartException, ItemException;

	public FoodCart reduceQuantity(FoodCart cart, Item item, int quantity) throws CartException, ItemException;

	public FoodCart removeItem(FoodCart cart, Item item) throws ItemException, CartException;

	public FoodCart clearCart(FoodCart cart) throws CartException;

	FoodCart increaseQuantity(FoodCart cart, Item item, Integer quantity) throws  ItemException;

}
