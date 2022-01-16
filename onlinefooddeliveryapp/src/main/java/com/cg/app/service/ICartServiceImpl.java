package com.cg.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.app.entity.FoodCart;
import com.cg.app.entity.Item;
import com.cg.app.exceptions.CartException;
import com.cg.app.exceptions.ItemException;
import com.cg.app.repository.ICartRepository;
import com.cg.app.repository.IItemRepository;

@Service
public class ICartServiceImpl implements ICartService {
	
	@Autowired
	private ICartRepository cartRepo;

	@Autowired
	IItemRepository itemRepo;

	
	

	@Override
	public FoodCart addItemToCart(FoodCart cart, Item item) throws CartException, ItemException {
	
		if((cart!=null) &&(item!=null)) {
			
			if(cartRepo.existsById(cart.getCartId())) {
				
				if(itemRepo.existsById(item.getItemId())) {
					
					Map<Item,Integer> item_cartQty = cart.getItem_cartQty();
					
					if(item_cartQty.containsKey(item)) {
						cart = increaseQuantity(cart,item,1);
						return cart;
					}
					
					item_cartQty.put(item, 1);
					
					return cartRepo.save(cart);
				  }
				
				
				throw new ItemException("Item with id "+item.getItemId()+" not found");
			}
			
			throw new CartException("Cart with id "+cart.getCartId()+" not found");
		}
		
		throw new CartException("Item/Cart not found");
	
	}

	
	@Override
	public FoodCart reduceQuantity(FoodCart cart, Item item, int quantity) throws CartException, ItemException {

	
		if(cart!=null && item!=null && quantity>0) {
			
			if(cartRepo.existsById(cart.getCartId())) {
				
				Map<Item,Integer> item_cartQty = cart.getItem_cartQty();
				
				if(item_cartQty.containsKey(item)) {
				
					Integer currQty = item_cartQty.get(item);
					
					if(quantity>=currQty) {
						//if the supplied quantity is big then remove all the items
						cart.removeItem(item);
						return cartRepo.save(cart);
					}
					
					Integer reducedQty =(currQty-quantity);
					
					cart.getItem_cartQty().put(item, reducedQty);
					
					return cartRepo.save(cart);
					}		
				}
				throw new ItemException(item.getItemName()+" not found in cart");
			}
		throw new CartException("Cart empty/notfound");

	}

	@Override
	public FoodCart removeItem(FoodCart cart, Item item) throws ItemException, CartException {

		
		if(cart!=null && item!=null) {
			
			if(cartRepo.existsById(cart.getCartId())) {
				
				if(itemRepo.existsById(item.getItemId())) {
					
					
					Map<Item,Integer> item_cartQty = cart.getItem_cartQty();
					
					if(item_cartQty.containsKey(item)) {
						cart.removeItem(item);
						cart = cartRepo.save(cart);
						return  cart;
						
					}
					else{
						throw new ItemException(item.getItemName()+" not found in cart");
					}
				}
			}
		}
		throw new CartException("Cart empty/notfound");
	}
		
		
		
		
		

	@Override
	public FoodCart clearCart(FoodCart cart) throws CartException {
		
		if(cart!=null) {
			if(cartRepo.existsById(cart.getCartId())) {
				cart.getItemList().clear();
				cart.getItem_cartQty().clear();
				if(cart.getItemList().size()>0)
					throw new CartException("Cart not empty");
				else
					return cartRepo.save(cart);
			}
		}
		throw new CartException("Cart empty/notfound");
	}

	@Override
	public FoodCart increaseQuantity(FoodCart cart, Item item, Integer quantity) throws ItemException {
		
		if((cart!=null) &&(item!=null)&&(quantity>0)){
	
			
			if(cartRepo.existsById(cart.getCartId())) {	
				
				Map<Item,Integer> item_cartQty = cart.getItem_cartQty();
				
				if(item_cartQty.containsKey(item)) {
				
					Integer currQty = item_cartQty.get(item);
					Integer availQty = item.getQuantity();
					
					if(currQty<availQty) {
						
						Integer increasedQty = (quantity+currQty);
						
						if((availQty-increasedQty) >= 0) {
							
							cart.getItem_cartQty().put(item,increasedQty);
							
							return cartRepo.save(cart);
						}
						throw new ItemException(item.getItemName()+" quantity exceeds available quantity");
					}
				}
				throw new ItemException(item.getItemName()+" quantity exceeds available quantity");
			}
		}
		
		throw new ItemException("Cart with id "+cart.getCartId()+" not found");
	}






	@Override
	public FoodCart viewCartById(Integer cartId) throws CartException {
		
		return cartRepo.findById(cartId).orElseThrow(() -> new CartException("Cart not found with id: "+cartId));
	}
	
}
