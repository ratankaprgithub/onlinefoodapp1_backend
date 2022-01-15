package com.cg.app.service;

import java.util.List;

import com.cg.app.entity.Customer;
import com.cg.app.exceptions.CustomerException;

public interface ICustomerService {
	public Customer addCustomer(Customer customer); 
	public Customer updateCustomer(Customer customer) throws CustomerException;
	public Customer removeCustomer(Integer customerId) throws CustomerException;
	public Customer viewCustomer(Integer customerId) throws CustomerException;
	public List<Customer> viewAllCustomers() throws CustomerException;
	
}
