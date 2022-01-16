package com.cg.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.app.entity.Customer;
import com.cg.app.entity.LoginBean;
import com.cg.app.service.ICustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	ICustomerService customerService;


/*	
	
	uri:- http://localhost:7000/customer/register
	method:- Post
	
	request data:- 
	
	
	{
	    "firstName": "Ram",
	    "lastName": "kumar",
	    "age": 32,
	    "gender": "M",
	    "mobileNumber": "8542122",
	    "email": "ram@gmail.com",
	    "password": "1234",
	    "address": {
	        "buildingName": "bl1",
	        "streetNumber": "st1",
	        "area": "ar1",
	        "city": "hyd",
	        "state": "tel",
	        "country": "ind",
	        "pincode": "423232"
	    }
	}
*/
	
	
	
	
	
	@PostMapping("/register")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
	
		Customer cust = customerService.addCustomer(customer);
		
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
		
		
	}
	
	/*
	
	
	url:- http://localhost:7000/customer/update
	method:- PUT
	
	request data:-
	
	{
    "customerId": 1,
    "firstName": "Ram",
    "lastName": "kumar",
    "age": 30,
    "gender": "M",
    "mobileNumber": "8542122",
    "email": "ram@gmail.com",
    "password": "1234",
    "address": {
        "addressId": 2,  
        "buildingName": "bl1",
        "streetNumber": "st1",
        "area": "ar1",
        "city": "hyd",
        "state": "tel",
        "country": "ind",
        "pincode": "423232"
    }
}
	
	
	
	
	
	*/
	
	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
	
		Customer cust = customerService.updateCustomer(customer);
		 
			return new ResponseEntity<Customer>(cust,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/view/{cid}")
	public ResponseEntity<Customer> viewCustomer(@PathVariable("cid") Integer cId){
		
		Customer customer= customerService.viewCustomer(cId);
		 
			return new ResponseEntity<Customer>(customer,HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/remove/{cid}")
	public Customer removeCustomer(@PathVariable("cid") Integer cId) {
		
		return  customerService.removeCustomer(cId);
		
	}
	
	@GetMapping("/viewall")
	public List<Customer> viewAllCustomers(){
		
		return customerService.viewAllCustomers();
		
		
	}
	
	@PostMapping("/login")
	public Customer loginCustomerHandler(@RequestBody LoginBean loginBean) {
		
	 	return customerService.authenticateCustomer(loginBean.getUsername(),loginBean.getPassword());
		
		
	}
	
	
	
	
}
