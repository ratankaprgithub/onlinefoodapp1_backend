package com.cg.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.app.entity.Customer;
import com.cg.app.entity.Item;
import com.cg.app.entity.OrderDetails;
import com.cg.app.entity.Restaurant;
import com.cg.app.exceptions.CartException;
import com.cg.app.exceptions.CustomerException;
import com.cg.app.exceptions.OrderException;
import com.cg.app.exceptions.RestaurantException;
import com.cg.app.repository.ICartRepository;
import com.cg.app.repository.ICustomerRepository;
import com.cg.app.repository.IOrderRepository;
import com.cg.app.repository.IRestaurantRepository;

@Service
public class IOrderServiceImpl implements IOrderService {

	
	@Autowired
	private  IOrderRepository orderRepo;
	
	@Autowired
	private IRestaurantRepository restaurantRepo;

	@Autowired
	private ICartRepository cartRepo;
	
	@Autowired
	private ICustomerRepository customerRepo;
	
	
	@Override
	public OrderDetails addOrder(OrderDetails order) throws OrderException, CartException {
	
		if(order!=null) {
		
			Optional<OrderDetails> existingOrder= orderRepo.findById(order.getOrderId());
			
			if(existingOrder.isPresent()) {
				throw new OrderException("Order already exist with id :"+order.getOrderId());
			}else {
				
					if(order.getCart() != null) {
						
						return orderRepo.save(order);
					}	
				
			}
			
			
		}
		throw new CartException("Check your cart Id");
		
		
		
	}

	@Override
	public OrderDetails updateOrder(OrderDetails order) throws OrderException {

		
		if(orderRepo.existsById(order.getOrderId()))
				return orderRepo.save(order);
		
		throw new OrderException("Invalid Order...");
		
		
		
		
		
	}

	@Override
	public OrderDetails removeOrder(Integer orderId) throws OrderException {
		

		 OrderDetails order= orderRepo.findById(orderId).orElseThrow(() -> new OrderException("Invlaid Order ID "+orderId));
		
		 orderRepo.delete(order);
		 
		 return order;
		
		
	}

	@Override
	public OrderDetails viewOrder(Integer orderId) throws OrderException {

		 OrderDetails order= orderRepo.findById(orderId).orElseThrow(() -> new OrderException("Invlaid Order ID "+orderId));
		
		 return order;
		
	}

	@Override
	public List<OrderDetails> viewAllOrdersByRestaurant(Integer restId) throws OrderException {
		
		Restaurant rest= restaurantRepo.findById(restId).orElseThrow(() -> new RestaurantException("Invalid Restaurent Id :"+restId));
		
		
		List<OrderDetails> allOrders= orderRepo.findAll();
		
		List<OrderDetails> orderRest = new ArrayList<OrderDetails>();
		
		for(OrderDetails order: allOrders) {
			
			Map<Item,Integer> cart= order.getCart().getItem_cartQty();

			Set<Item> items= cart.keySet();
			
			for(Item item:items) {
				
				if(item.getRestaurants().get(0).getRestaurantId() == rest.getRestaurantId()) {
					orderRest.add(order);
				}
				
			}
			
		}
		
		
		if(orderRest.size()>0) {
			return orderRest;
		}
		else
			throw new OrderException("from this Restaurent there is no any order");
		
	}

	@Override
	public List<OrderDetails> viewAllOrdersByCustomer(Integer customerId) throws OrderException, CustomerException {
		
		List<OrderDetails> orderList= orderRepo.getOrders(customerId);
		
		if(orderList.size() >0)
			return orderList;
		else
			throw new  OrderException("There is no any order for this customer");
		
	}

	
	
	
	
}
