package com.cg.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.app.entity.FoodCart;
import com.cg.app.entity.Item;
import com.cg.app.exceptions.CartException;
import com.cg.app.exceptions.ItemException;
import com.cg.app.service.ICartServiceImpl;
import com.cg.app.service.IItemServiceImpl;




@RequestMapping("/cart")
@RestController
public class CartController {
	
	
	@Autowired
	private ICartServiceImpl cartService;
	
	@Autowired
	private IItemServiceImpl itemService;
	

	@PostMapping("/additem/{cartid}")
	public FoodCart addItemToCart(@RequestBody Item item,@PathVariable("cartid") Integer cartId){	
		
		FoodCart cart = cartService.viewCartById(cartId);
		
		return cartService.addItemToCart(cart, item);

	}
	
	
	@GetMapping("/viewcart/{cartid}")
	public FoodCart viewCart(@PathVariable("cartid") Integer cartId) {
		return cartService.viewCartById(cartId);
	}

	
	@PutMapping("/increasequantity/{cartid}/{itemid}/{quantity}")
	public FoodCart increaseQuantity(@PathVariable("cartid") Integer cartId,@PathVariable("itemid") Integer itemId,@PathVariable("quantity") Integer quantity) {
		FoodCart cart = cartService.viewCartById(cartId);
		Item item = itemService.viewItem(itemId);
		return cartService.increaseQuantity(cart, item, quantity);
		
	}

	@PutMapping("/reducequantity/{cartid}/{itemid}/{quantity}")
	public FoodCart reduceQuantity(@PathVariable("cartid") Integer cartId,@PathVariable("itemid") Integer itemId,@PathVariable("quantity") Integer quantity) {
		FoodCart cart = cartService.viewCartById(cartId);
		Item item = itemService.viewItem(itemId);
		return cartService.reduceQuantity(cart, item, quantity);
	
	}

	@DeleteMapping("/removeitem/{cartid}/{itemid}")
	public FoodCart removeItem(@PathVariable("cartid") Integer cartId,@PathVariable("itemid") Integer itemId) {
		FoodCart cart = cartService.viewCartById(cartId);
		Item item = itemService.viewItem(itemId);
		return cartService.removeItem(cart, item);
	}

	@DeleteMapping("/clear/{cartid}")
	public FoodCart clearCart(@PathVariable("cartid") Integer cartId) {
		FoodCart cart = cartService.viewCartById(cartId);
	
		return cartService.clearCart(cart);

	}
	
	
	

}
