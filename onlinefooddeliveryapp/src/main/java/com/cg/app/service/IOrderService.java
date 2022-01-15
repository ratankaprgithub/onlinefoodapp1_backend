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
	public OrderDetails removeOrder(OrderDetails order) throws OrderException;
	public OrderDetails viewOrder(OrderDetails order) throws OrderException;
	public List<OrderDetails> viewAllOrders(Restaurant restaurant) throws OrderException;
	public List<OrderDetails> viewAllOrders(Customer customer) throws OrderException, CustomerException;

}
