package com.cg.app.controller;

import java.time.LocalDateTime;
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

import com.cg.app.entity.FoodCart;
import com.cg.app.entity.OrderDetails;
import com.cg.app.service.ICartService;
import com.cg.app.service.ICustomerService;
import com.cg.app.service.IOrderService;
import com.cg.app.service.IRestaurantService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IRestaurantService restaurantService;

	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private ICartService cartService;

	

	@PostMapping("/add/{cartid}")
	public OrderDetails addOrder(@PathVariable("cartid") Integer cartId){
		
		FoodCart cart = cartService.viewCartById(cartId);
		
		OrderDetails newOrder = new OrderDetails();
		
		
			newOrder.setCart(cart); //set cart
			newOrder.setOrderStatus("ORDER PENDING");  //set order status
		    newOrder.setOrderDate(LocalDateTime.now());  //set todays date as order date 
	
		    return orderService.addOrder(newOrder);
	}  
	
	@PutMapping("/update")
	public OrderDetails updateOrder(@RequestBody OrderDetails order) {
		return orderService.updateOrder(order);
	}
	
	@DeleteMapping("/remove/{orderid}")
	public OrderDetails removeOrder(@PathVariable("orderid") Integer ordId){
		return orderService.removeOrder(ordId);
	}
	
	
	@GetMapping("/view/{orderid}")
	public OrderDetails viewOrder(@PathVariable("orderid") Integer ordId) {
		return orderService.viewOrder(ordId);
	}
	
	@GetMapping("/viewallorders/restaurant/{restid}")
	public List<OrderDetails> viewAllOrders(@PathVariable("restid") Integer restId){
		
		return orderService.viewAllOrdersByRestaurant(restId);
	
	}
	@GetMapping("/viewallorders/customer/{custid}")
	public List<OrderDetails> viewAllOrders1(@PathVariable("custid") Integer custId) {
		
		return orderService.viewAllOrdersByCustomer(custId);
	
	}
} 
