package com.cg.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.app.entity.OrderDetails;

@Repository
public interface IOrderRepository extends JpaRepository<OrderDetails,Integer>{
	
	

}
