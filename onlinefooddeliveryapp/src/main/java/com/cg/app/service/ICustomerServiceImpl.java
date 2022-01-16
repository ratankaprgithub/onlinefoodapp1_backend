package com.cg.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.app.entity.Customer;
import com.cg.app.entity.FoodCart;
import com.cg.app.entity.Item;
import com.cg.app.exceptions.CustomerException;
import com.cg.app.exceptions.UserException;
import com.cg.app.repository.ICartRepository;
import com.cg.app.repository.ICustomerRepository;

@Service
public class ICustomerServiceImpl implements ICustomerService{

	
	
	@Autowired
	private ICustomerRepository customerRepo;
	
	@Autowired
	private ICartRepository cartRepo;
	
	
	@Override
	public Customer addCustomer(Customer customer) {
		
		Customer savedCustomer= customerRepo.save(customer);
		
		
		FoodCart cart = new FoodCart();
		
		//giving cartId as same as customer Id
		cart.setCartId(savedCustomer.getCustomerId());
	
		//associating FoodCart obj with the customer
		cart.setCustomer(customer);
		
		
		cartRepo.save(cart);
		
		return savedCustomer;
		
		
		
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
	
		if(customerRepo.existsById(customer.getCustomerId())) {
			return customerRepo.save(customer);
		}
		throw new CustomerException("Invalid Customer Data");
		
		
	}

	@Override
	public Customer removeCustomer(Integer customerId) throws CustomerException {

		
		Optional<Customer> opt= customerRepo.findById(customerId);
		
		if(opt.isPresent()) {
			
			Customer customer= opt.get();
			customerRepo.delete(customer);
			
			return customer;
			
		}
		else
			throw new CustomerException("Customer does not exist with Id :"+customerId);
		
		
		
	}

	@Override
	public Customer viewCustomer(Integer customerId) throws CustomerException {
		

		Optional<Customer> opt= customerRepo.findById(customerId);
		
		if(opt.isPresent()) {
			
			Customer customer= opt.get();	
			return customer;
			
		}
		else
			throw new CustomerException("Customer does not exist with Id :"+customerId);
		
		
	}

	@Override
	public List<Customer> viewAllCustomers() throws CustomerException {
	
		List<Customer> custList = customerRepo.findAll();
		if(custList.size()>0)
			return custList;
		throw new CustomerException("No Customers Found");
		
		
	}

	@Override
	public Customer authenticateCustomer(String username, String password) throws UserException {
		
		
		Customer customer= customerRepo.findByEmailAndPassword(username, password);
	
		if(customer == null)
			throw new UserException("Invalid Username or password..");
		else
			return customer;
		
	}


}
	