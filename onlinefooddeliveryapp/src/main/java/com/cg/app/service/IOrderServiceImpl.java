package com.cg.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.app.entity.Customer;
import com.cg.app.entity.OrderDetails;
import com.cg.app.entity.Restaurant;
import com.cg.app.exceptions.CartException;
import com.cg.app.exceptions.CustomerException;
import com.cg.app.exceptions.OrderException;

@Service
public class IOrderServiceImpl implements IOrderService {

	@Override
	public OrderDetails addOrder(OrderDetails order) throws OrderException, CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetails updateOrder(OrderDetails order) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetails removeOrder(OrderDetails order) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetails viewOrder(OrderDetails order) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetails> viewAllOrders(Restaurant restaurant) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetails> viewAllOrders(Customer customer) throws OrderException, CustomerException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
