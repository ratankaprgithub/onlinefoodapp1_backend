package com.cg.app.service;

import java.util.List;

import com.cg.app.entity.Customer;
import com.cg.app.entity.OrderDetails;
import com.cg.app.entity.Restaurant;
import com.cg.app.exceptions.CartException;
import com.cg.app.exceptions.CustomerException;
import com.cg.app.exceptions.OrderException;

public interface IOrderService {
	public OrderDetails addOrder(OrderDetails order) throws OrderException, CartException;
	public OrderDetails updateOrder(OrderDetails order) throws OrderException;
	public OrderDetails removeOrder(Integer orderId) throws OrderException;
	public OrderDetails viewOrder(Integer orderId) throws OrderException;
	public List<OrderDetails> viewAllOrdersByRestaurant(Integer restId) throws OrderException;
	public List<OrderDetails> viewAllOrdersByCustomer(Integer customerId) throws OrderException, CustomerException;

}
