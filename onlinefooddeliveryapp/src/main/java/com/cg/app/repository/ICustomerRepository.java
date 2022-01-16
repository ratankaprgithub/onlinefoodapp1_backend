package com.cg.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.cg.app.entity.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Integer>{
	
	public Customer findByEmailAndPassword(String email,String password);
	
	

}
